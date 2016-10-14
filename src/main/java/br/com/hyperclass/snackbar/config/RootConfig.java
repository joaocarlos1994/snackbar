package br.com.hyperclass.snackbar.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(SnackBarConfig.class)
public class RootConfig {
}