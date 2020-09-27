package com.ayushman.springboot.batch.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.ayushman.springboot.batch.model.Product;

@Configuration
public class BatchConfig {

	@Autowired
	private StepBuilderFactory sbf;

	@Autowired
	private JobBuilderFactory jbf;

	@Bean
	public Step step() {
		return sbf.get("step1").<Product, Product>chunk(3).reader(reader()).writer(writer()).processor(processor())
				.build();
	}

	@Bean
	public Job job() {
		return jbf.get("job1").incrementer(new RunIdIncrementer()).start(step()).build();
	}

	@Bean
	public ItemReader<Product> reader() {
		FlatFileItemReader<Product> reader = new FlatFileItemReader<>();
		reader.setResource(new ClassPathResource("products.csv"));

		DefaultLineMapper<Product> lineMapper = new DefaultLineMapper<>();
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setNames("id", "name", "description", "price");

		BeanWrapperFieldSetMapper<Product> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
		fieldSetMapper.setTargetType(Product.class);

		lineMapper.setLineTokenizer(lineTokenizer);
		lineMapper.setFieldSetMapper(fieldSetMapper);

		reader.setLineMapper(lineMapper);

		return reader;
	};

	@Bean
	public ItemProcessor<Product, Product> processor() {
		return (p -> {
			p.setPrice(p.getPrice() - 0.10 * p.getPrice());
			return p;
		});
	}

	@Bean
	public ItemWriter<Product> writer() {
		JdbcBatchItemWriter<Product> writer = new JdbcBatchItemWriter<>();
		writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Product>());
		writer.setSql("insert into product2 (id,name,description,price) values (:id,:name,:description,:price)");
		writer.setDataSource(dataSource());
		return writer;
	}

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/mydb");
		dataSource.setUsername("root");
		dataSource.setPassword("test1234");
		return dataSource;
	}

}
