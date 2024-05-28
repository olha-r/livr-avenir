package co.simplon.livravenir;

import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import co.simplon.livravenir.controllers.AuthController;
import co.simplon.livravenir.controllers.AuthorController;
import co.simplon.livravenir.controllers.BookController;
import co.simplon.livravenir.controllers.BookItemController;
import co.simplon.livravenir.controllers.CategoryController;
import co.simplon.livravenir.controllers.ConditionController;
import co.simplon.livravenir.controllers.LanguageController;
import co.simplon.livravenir.controllers.PublisherController;
import co.simplon.livravenir.controllers.UserController;

@TestConfiguration
class ControllerMocks {

    @Bean
    AuthController authController() {
	return Mockito.mock(AuthController.class);
    }

    @Bean
    AuthorController authorController() {
	return Mockito.mock(AuthorController.class);
    }

    @Bean
    BookController bookController() {
	return Mockito.mock(BookController.class);
    }

    @Bean
    BookItemController bookItemController() {
	return Mockito.mock(BookItemController.class);
    }

    @Bean
    CategoryController categoryController() {
	return Mockito.mock(CategoryController.class);
    }

    @Bean
    ConditionController conditionController() {
	return Mockito.mock(ConditionController.class);
    }

    @Bean
    LanguageController languageController() {
	return Mockito.mock(LanguageController.class);
    }

    @Bean
    PublisherController publisherController() {
	return Mockito.mock(PublisherController.class);
    }

    @Bean
    UserController userController() {
	return Mockito.mock(UserController.class);
    }
}