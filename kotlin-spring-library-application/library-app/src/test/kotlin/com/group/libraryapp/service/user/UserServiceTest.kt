package com.group.libraryapp.service.user

import com.group.libraryapp.domain.user.User
import com.group.libraryapp.domain.user.UserRepository
import com.group.libraryapp.dto.user.request.UserCreateRequest
import com.group.libraryapp.dto.user.request.UserUpdateRequest
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class UserServiceTest @Autowired constructor (
    private val userRepository: UserRepository,
    private val userService: UserService
) {

    @AfterEach
    fun afterEach() {
        userRepository.deleteAll()
    }

    @Test
    fun saveUserTest() {
        //given
        val request = UserCreateRequest("danadot", null)

        //when
        userService.saveUser(request)

        //then
        val results = userRepository.findAll()
        assertThat(results).hasSize(1)
        assertThat(results[0].name).isEqualTo("danadot")
        assertEquals(results[0].age, null) // kotlin -> age가 nullable인지 판단 불가 (java code) --> @Nullable annotation 추가
    }

    @Test
    fun getUsersTest() {
        //given
        userRepository.saveAll(listOf(User("danadot", null), User("mipong", 2)))

        //when
        val results = userService.getUsers()

        //then
        assertThat(results).hasSize(2)
        assertThat(results).extracting("name").containsExactlyInAnyOrder("danadot", "mipong")
    }

    @Test
    fun updateUserName() {
        // given
        val user = User("danadot", null)
        val savedUser = userRepository.save(user)

        // when
        userService.updateUserName(UserUpdateRequest(savedUser.id, "meepong"))

        // then
        assertEquals(userRepository.findById(savedUser.id).get().name, "meepong")
    }

    @Test
    fun deleteUser() {
        //given
        userRepository.save(User("heepong", 29))

        //when
        userService.deleteUser(User("heepong", 29).name)

        //then
        assertThat(userRepository.findByName("heepong")).isEmpty
    }

}