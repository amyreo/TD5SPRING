package com.inti;

import java.io.File;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class SuppressionFichierPersonne implements Tasklet {

	@Value("${inputFile}")
	Resource resource;
	
	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		File file = resource.getFile();
		
		System.out.println("chemin du fichier : " + file.getAbsolutePath());
		file.delete();
		return RepeatStatus.FINISHED;
	}

}
