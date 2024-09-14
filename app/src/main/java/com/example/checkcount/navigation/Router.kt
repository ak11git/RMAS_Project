package com.example.checkcount.navigation

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.checkcount.repository.Resource
import com.example.checkcount.model.Obj
import com.example.checkcount.model.User
import com.example.checkcount.screens.IndexScreen
import com.example.checkcount.viewModels.AuthViewModel
import com.example.checkcount.screens.LoginScreen
import com.example.checkcount.screens.ObjScreen
import com.example.checkcount.screens.RankingScreen
import com.example.checkcount.screens.RegisterScreen
import com.example.checkcount.screens.SettingScreen
import com.example.checkcount.screens.TableScreen
import com.example.checkcount.screens.UserProfileScreen
import com.example.checkcount.viewModels.ObjViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.Gson
import com.google.maps.android.compose.rememberCameraPositionState

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Router(
    viewModel: AuthViewModel,
    ObjViewModel: ObjViewModel
){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.loginScreen) {
        composable(Routes.loginScreen){
            LoginScreen(
                viewModel = viewModel,
                navController = navController
            )
        }
        composable(Routes.indexScreen){
            val objsResource = ObjViewModel.objs.collectAsState()
            val objMarkers = remember {
                mutableListOf<Obj>()
            }
            objsResource.value.let {
                when(it){
                    is Resource.Success -> {
                        objMarkers.clear()
                        objMarkers.addAll(it.result)
                    }
                    is Resource.loading -> {

                    }
                    is Resource.Failure -> {
                        Log.e("Podaci", it.toString())
                    }
                    null -> {}
                }
            }
            IndexScreen(
                viewModel = viewModel,
                navController = navController,
                objViewModel = ObjViewModel,
                objMarkers = objMarkers
            )
        }
        composable(
            route = Routes.indexScreenWithParams + "/{isCameraSet}/{latitude}/{longitude}",
            arguments = listOf(
                navArgument("isCameraSet") { type = NavType.BoolType },
                navArgument("latitude") { type = NavType.FloatType },
                navArgument("longitude") { type = NavType.FloatType }
            )
        ) { backStackEntry ->
            val isCameraSet = backStackEntry.arguments?.getBoolean("isCameraSet")
            val latitude = backStackEntry.arguments?.getFloat("latitude")
            val longitude = backStackEntry.arguments?.getFloat("longitude")

            val objsResource = ObjViewModel.objs.collectAsState()
            val objMarkers = remember {
                mutableListOf<Obj>()
            }
            objsResource.value.let {
                when(it){
                    is Resource.Success -> {
                        objMarkers.clear()
                        objMarkers.addAll(it.result)
                    }
                    is Resource.loading -> {

                    }
                    is Resource.Failure -> {
                        Log.e("Podaci", it.toString())
                    }
                    null -> {}
                }
            }

            IndexScreen(
                viewModel = viewModel,
                navController = navController,
                objViewModel = ObjViewModel,
                isCameraSet = remember { mutableStateOf(isCameraSet!!) },
                cameraPositionState = rememberCameraPositionState {
                    position = CameraPosition.fromLatLngZoom(LatLng(latitude!!.toDouble(), longitude!!.toDouble()), 17f)
                },
                objMarkers = objMarkers
            )
        }
        composable(
            route = Routes.indexScreenWithParams + "/{isCameraSet}/{latitude}/{longitude}/{objs}",
            arguments = listOf(
                navArgument("isCameraSet") { type = NavType.BoolType },
                navArgument("latitude") { type = NavType.FloatType },
                navArgument("longitude") { type = NavType.FloatType },
                navArgument("objs") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val isCameraSet = backStackEntry.arguments?.getBoolean("isCameraSet")
            val latitude = backStackEntry.arguments?.getFloat("latitude")
            val longitude = backStackEntry.arguments?.getFloat("longitude")
            val objsJson = backStackEntry.arguments?.getString("objs")
            val objs = Gson().fromJson(objsJson, Array<Obj>::class.java).toList()

            IndexScreen(
                viewModel = viewModel,
                navController = navController,
                objViewModel = ObjViewModel,
                isCameraSet = remember { mutableStateOf(isCameraSet!!) },
                cameraPositionState = rememberCameraPositionState {
                    position = CameraPosition.fromLatLngZoom(LatLng(latitude!!.toDouble(), longitude!!.toDouble()), 17f)
                },
                objMarkers = objs.toMutableList(),
                isFilteredParam = true
            )
        }
        composable(
            route = Routes.indexScreenWithParams + "/{objs}",
            arguments = listOf(
                navArgument("objs") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val objsJson = backStackEntry.arguments?.getString("objs")
            val objs = Gson().fromJson(objsJson, Array<Obj>::class.java).toList()
            IndexScreen(
                viewModel = viewModel,
                navController = navController,
                objViewModel = ObjViewModel,
                objMarkers = objs.toMutableList(),
                isFilteredParam = true
            )
        }
        composable(Routes.registerScreen){
            RegisterScreen(
                viewModel = viewModel,
                navController = navController
            )
        }
        composable(
            route = Routes.objScreen + "/{obj}",
            arguments = listOf(
                navArgument("obj"){ type = NavType.StringType }
            )
        ){backStackEntry ->
            val objJson = backStackEntry.arguments?.getString("obj")
            val obj = Gson().fromJson(objJson, Obj::class.java)
            ObjViewModel.getObjAllRates(obj.id)
            ObjScreen(
                obj = obj,
                navController = navController,
                objViewModel = ObjViewModel,
                viewModel = viewModel,
                objs = null
            )
        }
        composable(
            route = Routes.objScreen + "/{obj}/{objs}",
            arguments = listOf(
                navArgument("obj"){ type = NavType.StringType },
                navArgument("objs"){ type = NavType.StringType },
            )
        ){backStackEntry ->
            val objsJson = backStackEntry.arguments?.getString("objs")
            val objs = Gson().fromJson(objsJson, Array<Obj>::class.java).toList()
            val objJson = backStackEntry.arguments?.getString("obj")
            val obj = Gson().fromJson(objJson, Obj::class.java)
            ObjViewModel.getObjAllRates(obj.id)

            ObjScreen(
                obj = obj,
                navController = navController,
                objViewModel = ObjViewModel,
                viewModel = viewModel,
                objs = objs.toMutableList()
            )
        }
        composable(
            route = Routes.userProfileScreen + "/{userData}",
            arguments = listOf(navArgument("userData"){
                type = NavType.StringType
            })
        ){backStackEntry ->
            val userDataJson = backStackEntry.arguments?.getString("userData")
            val userData = Gson().fromJson(userDataJson, User::class.java)
            val isMy = FirebaseAuth.getInstance().currentUser?.uid == userData.id
            UserProfileScreen(
                navController = navController,
                viewModel = viewModel,
                objViewModel = ObjViewModel,
                userData = userData,
                isMy = isMy
            )
        }
        composable(
            route = Routes.tableScreen + "/{objs}",
            arguments = listOf(navArgument("objs") { type = NavType.StringType })
        ){ backStackEntry ->
            val objsJson = backStackEntry.arguments?.getString("objs")
            val objs = Gson().fromJson(objsJson, Array<Obj>::class.java).toList()
            TableScreen(objs = objs, navController = navController, objViewModel = ObjViewModel)
        }
        
        composable(Routes.settingsScreen){
            SettingScreen(navController = navController)
        }
        composable(Routes.rankingScreen){
            RankingScreen(
                viewModel = viewModel,
                navController = navController
            )
        }
    }
}