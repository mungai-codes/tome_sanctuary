package com.mungaicodes.tomesanctuary.presentation.home.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mungaicodes.tomesanctuary.R
import com.mungaicodes.tomesanctuary.presentation.ui.theme.LampLight
import com.mungaicodes.tomesanctuary.presentation.ui.theme.TomeSanctuaryTheme
import java.util.*

@Composable
fun CategoryItem(
    category: Category,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {

    Card(
        modifier = modifier
            .size(120.dp)
            .clickable {
                onClick()
            }
            .shadow(10.dp, RectangleShape)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            ),
        shape = RoundedCornerShape(10.dp),
        elevation = 8.dp,
        backgroundColor = LampLight,
        border = BorderStroke(2.dp, Color.LightGray)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(
                    top = 10.dp,
                    start = 14.dp,
                    end = 14.dp,
                    bottom = 5.dp
                )
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioHighBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                ),
        ) {
            Box(modifier = Modifier.size(50.dp)) {
                Image(
                    painter = painterResource(category.image),
                    contentDescription = category.label,
                    contentScale = ContentScale.FillBounds
                )
            }
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = category.label,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif,
                textAlign = TextAlign.Center,
                color = Color.DarkGray
            )
        }

    }

}

@Preview(showBackground = true)
@Composable
fun CatetegoryCardPreview() {
    TomeSanctuaryTheme {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier =
            Modifier.fillMaxSize()
        ) {
            CategoryItem(
                category = Category(
                    label = "Art & Literature",
                    image = R.drawable.code_icon,
                )
            ) {

            }

        }
    }
}