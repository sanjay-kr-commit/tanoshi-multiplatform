package tanoshi.multiplatform.common.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.onClick
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import tanoshi.multiplatform.common.model.MainScreenViewModel
import tanoshi.multiplatform.common.naviagtion.CreateScreenCatalog
import tanoshi.multiplatform.common.naviagtion.Screen
import tanoshi.multiplatform.shared.SharedApplicationData

@Composable
fun MainScreen(
    sharedAppData : SharedApplicationData ,
    viewModel : MainScreenViewModel
) = sharedAppData.run {
    if ( portrait ) PortraitMainScreen( viewModel )
    else LandscapeMainScreen( viewModel )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun SharedApplicationData.PortraitMainScreen(
    viewModel : MainScreenViewModel
) {
    Scaffold(
       bottomBar = {
           Row(
               modifier = Modifier
                   .fillMaxWidth()
                   .wrapContentHeight()
                   .padding( 5.dp ) ,
               horizontalArrangement = Arrangement.SpaceEvenly ,
               verticalAlignment = Alignment.CenterVertically
           ) {
               MainScreen.entries.forEach { screen ->
                   Row(
                       Modifier.onClick {
                           viewModel.navController navigateTo screen.name
                       } ,
                       verticalAlignment = Alignment.CenterVertically
                   ) {  
                       Icon(
                           screen.icon ,
                           screen.label 
                       )
                       AnimatedVisibility(
                           viewModel.navController.currentScreen == screen.name
                       ) {
                           Text(
                               " ${screen.label}" ,
                               modifier = Modifier
                                   .align(Alignment.CenterVertically)
                           )
                       }
                   }
               }
           }
       } 
    ) {
        Box( Modifier.fillMaxSize().padding( it ) , contentAlignment = Alignment.Center ) {
            MainScreenCatalog( viewModel = viewModel )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun SharedApplicationData.LandscapeMainScreen( viewModel: MainScreenViewModel ) {
    Row (
        modifier = Modifier
            .fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .wrapContentWidth()
                .padding( 5.dp ) ,
            horizontalAlignment = Alignment.CenterHorizontally ,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            MainScreen.entries.forEach { screen ->
                 Column(
                     modifier = Modifier
                         .onClick {
                             viewModel.navController navigateTo screen.name
                         }
                 ) {
                     Icon(
                         screen.icon ,
                         screen.label
                     )
//                     AnimatedVisibility(
//                         viewModel.navController.currentScreen == screen.label
//                     ) {
//                         Text( screen.label ,
//                               modifier = Modifier
//                                   .align(Alignment.CenterHorizontally)
//                         )
//                     }
                 }
            }
            
        }
        
        
        MainScreenCatalog( viewModel )
        
        
    }
}

enum class MainScreen(
    val label : String ,
    val icon : ImageVector
) {
    BrowseScreen(
        "Browse Screen" ,
        Icons.Filled.Home
    ) ,
    LogScreen(
        "Log Screen" ,
        Icons.Filled.List
    )
}

@Composable
private fun SharedApplicationData.MainScreenCatalog(
    viewModel : MainScreenViewModel
) {
    CreateScreenCatalog( viewModel.navController ) {
        Screen( MainScreen.BrowseScreen.name ) {
            BrowseScreen( this@MainScreenCatalog )
        }
        Screen( MainScreen.LogScreen.name ) {
            LogScreen( logger )
        }
    }
}