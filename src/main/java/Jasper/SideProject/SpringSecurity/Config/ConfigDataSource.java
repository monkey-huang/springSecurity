package Jasper.SideProject.SpringSecurity.Config;


import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

//@Configuration
//public class ConfigDataSource {
//
//	@Bean
//	DataSource dataSource() {
//	    return new EmbeddedDatabaseBuilder()
//	        .setType(EmbeddedDatabaseType.HSQL)
//	        .addScript("jdbc:mysql://localhost:3306/test_schema?serverTimezone=UTC&useSSL=false")
//	        .build();
//	}
//}
