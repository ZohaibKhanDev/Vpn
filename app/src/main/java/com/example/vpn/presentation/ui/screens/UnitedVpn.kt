package com.example.vpn.presentation.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowCircleDown
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.MenuOpen
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vpn.R
import com.example.vpn.domain.model.vpn.UnitedState
import com.example.vpn.domain.usecase.ResultState
import com.example.vpn.presentation.viewmodel.MainViewModel
import org.koin.compose.koinInject

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun UnitedVpn() {
    val viewModel: MainViewModel = koinInject()
    val state by viewModel.allVpn.collectAsState()
    var isLoading by remember { mutableStateOf(false) }
    var UnitedData by remember { mutableStateOf<UnitedState?>(null) }
    var isConnected by remember { mutableStateOf(false) }

    when (state) {
        is ResultState.Error -> {
            val error = (state as ResultState.Error).error
            Text(text = error.toString())
            isLoading = false
        }

        ResultState.Loading -> {
            isLoading = true
        }

        is ResultState.Success -> {
            val success = (state as ResultState.Success).success
            UnitedData = success
            isLoading = false
        }
    }

    val scroll = rememberScrollState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Vpn", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Medium
                    )
                },
                navigationIcon = {
                    Icon(imageVector = Icons.Outlined.MenuOpen, contentDescription = "", tint = Color.White)
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0XFF0b98fa))
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scroll)
                .background(Color.White)
                .padding(top = it.calculateTopPadding(), bottom = 20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(bottomStart = 200.dp, bottomEnd = 200.dp))
                    .fillMaxWidth()
                    .background(Color(0XFF0b98fa))
                    .height(290.dp),
                contentAlignment = Alignment.Center
            ) {
                Column {
                    Box(
                        modifier = Modifier
                            .size(120.dp)
                            .clickable {
                                if (isConnected) {
                                    viewModel.disconnectVpn()
                                    isConnected = false
                                } else {
                                    viewModel.getUnitedVon()
                                    isConnected = true
                                }
                                isLoading = true
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.whitecircal),
                            contentDescription = "",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                        Image(
                            painter = painterResource(id = R.drawable.power),
                            contentDescription = "",
                            contentScale = ContentScale.Crop,
                            alignment = Alignment.Center,
                            modifier = Modifier.size(50.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(15.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .clip(CircleShape)
                                .size(8.dp)
                                .background(if (!isLoading && isConnected) Color.Green else Color.Red)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = if (isConnected) "Connected" else "Disconnected",
                            color = Color.White,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }


            Text(
                text = "Speed",
                color = Color.Gray,
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 110.dp, top = 5.dp),
                horizontalArrangement = Arrangement.spacedBy(22.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
                    Text(text = "Download", color = Color(0XFF61bffc))
                    Text(
                        text = "10.5",
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.Gray,
                        fontSize = 23.sp
                    )
                    Text(text = "mbs", color = Color.Gray, fontSize = 15.sp)
                }

                VerticalDivider(
                    color = Color.Gray,
                    modifier = Modifier.height(90.dp),
                    thickness = 4.dp
                )

                Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
                    Text(text = "Upload", color = Color(0XFF61bffc))
                    Text(
                        text = "7.3",
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.Gray,
                        fontSize = 23.sp
                    )
                    Text(text = "mbs", color = Color.Gray, fontSize = 15.sp)
                }
            }

            Spacer(modifier = Modifier.height(17.dp))

            Text(text = "Location", color = Color.Gray, fontSize = 20.sp)

            Image(
                painter = painterResource(id = R.drawable.map),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            )

            Spacer(modifier = Modifier.height(14.dp))

            Row(
                modifier = Modifier
                    .width(230.dp)
                    .height(55.dp)
                    .border(3.dp, color = Color(0XFF61bffc), shape = RoundedCornerShape(23.dp)),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                Image(
                    painter = painterResource(id = R.drawable.unitedflag),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(25.dp)
                        .clip(CircleShape)
                )

                Text(
                    text = "United State",
                    color = Color.Gray,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Icon(
                    imageVector = Icons.Outlined.KeyboardArrowDown,
                    contentDescription = "",
                    tint = Color.Gray
                )

            }
        }
    }
}
