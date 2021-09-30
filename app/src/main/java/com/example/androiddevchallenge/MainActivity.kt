/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.ui.Screen
import com.example.androiddevchallenge.ui.screens.authentication.*
import com.example.androiddevchallenge.ui.screens.content.ContentScreen
import com.example.androiddevchallenge.ui.screens.content.ContentViewModel
import com.example.androiddevchallenge.ui.theme.MyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val loginViewModel: LoginViewModel by viewModels()
    private val createAccountViewModel: CreateAccountViewModel by viewModels()
    private val contentViewModel: ContentViewModel by viewModels()

    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            MyTheme {
                MyApp {
                    NavHost(
                        navController = navController,
                        startDestination = Screen.AuthenticationScreen.route
                    ) {
                        composable(Screen.AuthenticationScreen.route) {
                            AuthenticationScreen(navController)
                        }
                        composable(Screen.LoginScreen.route) {
                            LoginScreen(
                                navController = navController,
                                loginViewModel = loginViewModel)
                        }
                        composable(Screen.CreateAccountScreen.route) {
                            CreateAccountScreen(
                                navController = navController,
                                viewModel = createAccountViewModel
                            )
                        }
                        composable(Screen.ContentScreen.route) { ContentScreen(contentViewModel) }
                    }
                }
            }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    Surface(color = MaterialTheme.colors.background) {
        content()
    }
}