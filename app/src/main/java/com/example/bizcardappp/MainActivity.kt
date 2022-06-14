package com.example.bizcardappp

import android.content.ContentValues.TAG
import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.ColorUtils
import com.example.bizcardappp.ui.theme.BizCardApppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BizCardApppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    CreateBizCard()
                }
            }
        }
    }
}

// our composable function
@Composable
fun CreateBizCard() {
    // special variable to remember our button state
    val buttonClickedState = remember {
        // mutable state of method which is gonna be false
        mutableStateOf(false)
    }

    // inside it we have a Surface that stores other views
    // inside it, in properties of surface we pass modifier
    // that allows us to modify surface's properties, in
    // this case we set it's width and height to max
    Surface(modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth()) {
        // now here we add a card view and in it's modifier
        // we set height and width( can also be fill max)
        // shape to rounded corners and elevation to 4 dp
        // which gives us elevated view effect by adding shadows
        // and at last padding which is different from xml padding
        // it is more life space around the view not inside the view
        Card(

            modifier = Modifier
                .width(200.dp)
                .height(390.dp)
                .padding(12.dp),
            shape = RoundedCornerShape(corner =
            CornerSize(15.dp)),
            elevation = 4.dp,

        ) { Column(
            modifier = Modifier.height(300.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally) {

            // so surface is pretty similar to the card view
            CreateImageProfile()

            Divider(thickness = 1.dp)

            CreateInfo()

            Button(onClick = {
                // changing the button clicked sate value to true on click
                // now here we are toggling it's value
                // so we are setting it to opposite of whatever the value is
                buttonClickedState.value = !buttonClickedState.value

             }
            ) {

                Text(
                    text = "Portfolio",
                    style = MaterialTheme.typography.button
                )

            }
            if (buttonClickedState.value) {
                // if button click value is true
                Content()
            } else {
                // if false we gonna show empty box
                Box() {

                }
            }
        }

        }


        }
    }


@Composable
private fun CreateInfo() {
    Column(modifier = Modifier.padding(4.dp)) {

        Text(
            text = "Jessey",
            color = MaterialTheme.colors.primary,
            fontSize = 25.sp,
            style = MaterialTheme.typography.h4
        )

        Text(
            text = "Android compose developer",
            modifier = Modifier.padding(3.dp)
        )

        Text(
            text = "@jesseyJackq97",
            modifier = Modifier.padding(3.dp),
            style = MaterialTheme.typography.subtitle1
        )

    }
}

@Composable
fun Content() {

    Box(modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth()
        .padding(5.dp)) {

        Surface(modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(3.dp),
            shape = RoundedCornerShape(corner = CornerSize(6.dp)),
            border = BorderStroke(width = 2.dp,
            color = Color.LightGray)
            ) {

            Portfolio(data = listOf("Project 1", "Project 2", "Project 3"))

        }

    }

}

@Composable
fun Portfolio(data: List<String>) {
    LazyColumn{

        items(data){ item ->

            Card(modifier = Modifier
                .padding(13.dp)
                .fillMaxWidth(),
                shape = RectangleShape,
                elevation = 4.dp

            ) {

                Row(
                    modifier = Modifier
                        .padding(8.dp)
                        .background(MaterialTheme.colors.surface)
                        .padding(16.dp)
                ) {

                    CreateImageProfile(modifier = Modifier.size(100.dp))

                    Column(modifier = Modifier
                        .padding(7.dp)
                        .align(alignment = Alignment.CenterVertically)) {

                        Text(text = item, fontWeight = FontWeight.Bold)
                        Text(text = "A great project",
                            style = MaterialTheme.typography.body2
                        )
                    }

                }

            }


        }
    }
}


// a different function for our image profile elements
// creating different functions allows us to manage our code better
// you can also pass attributes in the parameters
// to avoid errors we can also set a default modifier in the parameter
@Composable
private fun CreateImageProfile(modifier: Modifier = Modifier) {

    // surface is like a card view

    Surface(

        modifier = Modifier
            .size(125.dp)
            .padding(12.dp),
        shape = CircleShape,
        border = BorderStroke(0.5.dp, Color.LightGray),
        elevation = 4.dp,
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.5f)
    ) {

        Image(
            painter = painterResource(id = R.drawable.ic_profile_image),
            contentDescription = "profile image",
            modifier = modifier.size(135.dp),

            )
    }
}

//@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BizCardApppTheme {
        CreateBizCard()
    }
}


