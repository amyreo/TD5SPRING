package com.inti.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.inti.model.Personne;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	@Autowired
	private ItemReader<Personne> itemReader;
	@Autowired
	private ItemProcessor<Personne, Personne> itemProcessor;
	@Autowired
	private ItemWriter<Personne> itemWriter;
	@Autowired
	private Tasklet tasklet;
	
	@Bean
	public Job job(@Qualifier("step1") Step step1, @Qualifier("step2") Step step2) {
		return jobBuilderFactory.get("Personne")
				.start(step1)
				.next(step2)
				.build();
	}
	
	@Bean
	public Step step1() {
		return stepBuilderFactory
				.get("Première étape de notre batch : lecture du fichier TD5-excel.csv")
				.<Personne, Personne>chunk(10)
				.reader(itemReader)
				.processor(itemProcessor)
				.writer(itemWriter)
				.build();
	}
	
	@Bean
	public Step step2() {
		return stepBuilderFactory
				.get("Deuxième étape de notre batch : suppression du fichier dataUniv.csv")
				.tasklet(tasklet)
				.build();
	}
	
	@Bean
	public FlatFileItemReader<Personne> reader(
			@org.springframework.beans.factory.annotation.Value("${inputFile}") org.springframework.core.io.Resource resource) {
		FlatFileItemReader<Personne> fileItemReader = new FlatFileItemReader<>();
		fileItemReader.setName("Univ1");
		fileItemReader.setLinesToSkip(1);
		fileItemReader.setResource(resource);
		fileItemReader.setLineMapper(lineMap());
		return fileItemReader;
	}

	@Bean
	public LineMapper<Personne> lineMap() {
		DefaultLineMapper<Personne> lineMapper = new DefaultLineMapper<>();
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		BeanWrapperFieldSetMapper<Personne> beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<>();
		lineTokenizer.setDelimiter(",");
		lineTokenizer.setStrict(false);
		lineTokenizer.setNames("nom", "prenom", "salaire");
		lineMapper.setLineTokenizer(lineTokenizer);
		beanWrapperFieldSetMapper.setTargetType(Personne.class);
		lineMapper.setFieldSetMapper(beanWrapperFieldSetMapper);
		return lineMapper;

	}

}
