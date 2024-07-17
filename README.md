# TouchE-Plugin

## Overview
"TouchePlugin" is a Kotlin package designed to streamline the integration of touch-based interactions within your android applications.

## Installation
The Package Manager is a tool for automating the distribution of Android kotlin code and is integrated into the AAR file.

Once you have your AAR file added to your project, you have to add some dependencies & AAR file in to your project which given below

    implementation(files("../plugin/toucheplugin.aar"))

    implementation("com.intuit.sdp:sdp-android:1.1.0")
    implementation("com.intuit.ssp:ssp-android:1.1.0")

    // Rest API
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.1")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.10.0")

    // Coroutines"
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.1")
    implementation("androidx.fragment:fragment-ktx:1.4.1")

    // Lifecycle
    implementation("androidx.lifecycle:lifecycle-common:2.4.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.1")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.4.1")

    implementation("androidx.preference:preference-ktx:1.2.0")

    // Glide
    implementation("com.github.bumptech.glide:glide:4.16.0")

    // amazon aws
    implementation("com.amazonaws:aws-android-sdk-s3:2.17.1")

    // Dot Indicator
    implementation("ru.tinkoff.scrollingpagerindicator:scrollingpagerindicator:1.0.0")
    implementation("com.tbuonomo:dotsindicator:5.0")

    // Vertical Seekbar
    implementation("com.h6ah4i.android.widget.verticalseekbar:verticalseekbar:1.0.0")

    //exoplayer
    implementation("com.google.android.exoplayer:exoplayer-core:2.17.1")
    implementation("com.google.android.exoplayer:exoplayer-dash:2.17.1")
    implementation("com.google.android.exoplayer:exoplayer-ui:2.17.1")
    implementation("com.google.android.exoplayer:exoplayer-ui:2.17.1")
    implementation("com.google.android.exoplayer:exoplayer-hls:2.17.1")
    implementation("com.google.android.exoplayer:exoplayer-rtsp:2.17.1")


## Usage
### Usage of AAR file
In your Android studio project just add TouchEPlugin.aar which is given above.

After that First check your server URL is valid or not and User are already login or not in TouchEPlugin using validateURLAndToken method.

```
    private lateinit var login: OpenClass

    val urlString = "https://api-cluster.system.touchetv.com"
    var userToken = ""

        login = OpenClass(this)

        login.validateURLAndToken(urlString, userToken) { isURLValid, isTokenValid ->
            Log.d("TouchEPlugin Log", "urlString: $urlString, $userToken")
            Log.d("TouchEPlugin Log", "validateURLAndToken: $isURLValid, $isTokenValid")
        }
```

## Login

Login using userAuthentication method in which you have to just pass username and password.

After successfully login save user data in your project.

```
            val loginRequest = LoginRequest(
                password = binding.edtPassword.text.toString().trim(),
                email = binding.edtEmail.text.toString().trim()
            )

            login.userAuthentication(loginRequest) { response ->
                if (response?.isSuccessful == true) {
                    processLogin(response.body())
                } else {
                    showToast("Error logging you in")
                }
            }

```

## Get Movie list Data

Get movie list using getMovieDetail method

```
    private lateinit var homeScreen: OpenClass
    private var loginResponse: LoginResponse? = null
    private var data: List<AllContentsResponse>? = null

            // Use this method for get movie list
            homeScreen = OpenClass(this)

            homeScreen.getMovieDetail {
                Log.d("TouchEPlugin Log", "getMovieDetail: $it")
            }

            // Use this method for get my cart count
              homeScreen.getCartDataCount {
                Log.d("TouchEPlugin Log", "getCartDataCount: $it")
            }

```

## Navigation to Movie Details Screen

```
val intent = Intent(this, MovieDetailActivity::class.java)
intent.putExtra("movieID", movieID)
startActivity(intent)
```

## Navigation to My Cart Screen

```
startActivity(Intent(this, CartActivity::class.java))
```
