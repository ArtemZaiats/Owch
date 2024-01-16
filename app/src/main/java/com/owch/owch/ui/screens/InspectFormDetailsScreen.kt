package com.owch.owch.ui.screens

import android.content.Context
import android.util.Log
import android.widget.RadioGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.owch.owch.data.InspectCard
import com.owch.owch.ui.cards.ChoiceCard
import com.owch.owch.ui.cards.MultiCheckCard
import com.owch.owch.ui.cards.OdometerCard
import com.owch.owch.ui.cards.OtherCard
import com.owch.owch.ui.cards.PhotoCard
import com.owch.owch.ui.cards.SignCard
import com.owch.owch.ui.cards.SingleCheckCard
import com.owch.owch.ui.elements.ContainerWithToolbar
import com.owch.owch.utils.PreferencesManager
import com.owch.owch.viewModel.FormViewModel
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.PlatformDependentDeclarationFilter.All

@Composable
fun InspectFormDetailsScreen(navHostController: NavHostController, formId: String) {

    Log.d("InspectFormDetailsScreen", "formId: $formId")

    val formViewModel: FormViewModel = viewModel()

    val cards by formViewModel.cards.collectAsState()
    val context: Context = LocalContext.current
    val preferencesManager = remember { PreferencesManager(context) }
    val token = "Bearer ${preferencesManager.getToken("token", "")}"

    formViewModel.fetchCardsByFormId(token, formId)

    Column {
        ContainerWithToolbar(
            title = "Form details",
            onBackIconCLick = { navHostController.popBackStack() }
        ) { }
        LazyColumn(modifier = Modifier.padding(bottom = 8.dp)) {
            items(cards) {
                Spacer(modifier = Modifier.height(8.dp))
                when (it.cardTypeId) {
                    1 -> {
                        MultiCheckCard(card = it)
                    }

                    2 -> {
                        SignCard(card = it)
                    }

                    3 -> {
                        SingleCheckCard(card = it)
                    }

                    4 -> {
                        ChoiceCard(card = it)
                    }

                    5 -> {
                        OdometerCard(card = it)
                    }

                    6 -> {
                        PhotoCard(card = it)
                    }

                    7 -> {
                        OtherCard(card = it)
                    }
                }
            }
        }
//        Text(text = cards.toString())
    }
}