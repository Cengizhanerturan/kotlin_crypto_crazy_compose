package com.gcyazilim.cryptocrazycompose.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.gcyazilim.cryptocrazycompose.model.Crypto
import com.gcyazilim.cryptocrazycompose.util.Resource
import com.gcyazilim.cryptocrazycompose.viewmodel.CryptoDetailViewModel
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun CryptoDetailScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    id: String,
    price: String,
    viewModel: CryptoDetailViewModel = hiltViewModel()
) {
    //! 1 nci yol - Yanlıs kullanım
    /*
    val scope = rememberCoroutineScope()

    var cryptoItem by remember {
        mutableStateOf<Resource<Crypto>>(Resource.Loading())
    }

    scope.launch {
        cryptoItem = viewModel.getCrypto()
        println(cryptoItem.data)
    }*/

    //! 2 nci Yol - Dogru kullanım
    /*var cryptoItem by remember {
        mutableStateOf<Resource<Crypto>>(Resource.Loading())
    }

    LaunchedEffect(key1 = Unit) {
        cryptoItem = viewModel.getCrypto()
        println(cryptoItem.data)
    }*/

    //? 3 ncü Yol - En dogru kullanım
    /*val cryptoItem by produceState<Resource<Crypto>>(initialValue = Resource.Loading()) {
        value = viewModel.getCrypto()
    }*/
    val cryptoItem = produceState<Resource<Crypto>>(initialValue = Resource.Loading()) {
        value = viewModel.getCrypto()
    }.value

    Scaffold { contentPadding ->
        Box(
            modifier = Modifier
                .padding(contentPadding)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.secondary),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                when (cryptoItem) {
                    is Resource.Success -> {
                        val selectedCrypto = cryptoItem.data!![0]
                        Text(
                            text = selectedCrypto.name,
                            style = MaterialTheme.typography.displayLarge,
                            modifier = Modifier.padding(2.dp),
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary,
                            textAlign = TextAlign.Center
                        )

                        Image(
                            painter = rememberImagePainter(data = selectedCrypto.logoUrl),
                            contentDescription = selectedCrypto.name,
                            modifier = Modifier
                                .padding(16.dp)
                                .size(200.dp, 200.dp)
                                .clip(CircleShape)
                                .border(2.dp, Color.Gray, CircleShape)
                        )

                        Text(
                            text = price,
                            style = MaterialTheme.typography.displayMedium,
                            modifier = Modifier.padding(2.dp),
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.tertiary,
                            textAlign = TextAlign.Center
                        )
                    }

                    is Resource.Error -> {
                        Text(text = cryptoItem.message!!)
                    }

                    is Resource.Loading -> {
                        CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                    }
                }
            }

        }
    }

}