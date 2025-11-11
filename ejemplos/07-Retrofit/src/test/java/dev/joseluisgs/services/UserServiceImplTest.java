package dev.joseluisgs.services;


import dev.joseluisgs.repository.UserRemoteRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRemoteRepository userRepository;

    @InjectMocks
    private UserService userService;


    //@Test
   /* void testGetUserByIdWithRxJava_Success() {
        // Given
        int userId = 1;
        UserGetById expectedUser = createUserGetById(userId, "John", "Doe", "john@example.com", "avatar.jpg");

        when(userRepository.getUserByIdWithRxJava(userId))
                .thenReturn(Single.just(expectedUser));

        // When & Then
        userService.getUserByIdWithRxJava(userId)
                .test()
                .assertComplete()
                .assertNoErrors()
                .assertValue(expectedUser);

        verify(userRepository).getUserByIdWithRxJava(userId);
    }

    .test(): Crea un TestObserver que permite hacer aserciones sobre el comportamiento del stream
    .assertComplete(): Verifica que el stream se completó exitosamente
    .assertNoErrors(): Verifica que no hubo errores
    .assertValue(): Para Single, verifica el valor emitido
    .assertValues(): Para Observable, verifica todos los valores emitidos
    .assertValueCount(): Verifica el número de valores emitidos
    .assertError(): Verifica que se emitió un error específico
    .assertErrorMessage(): Verifica el mensaje del error


    */

}