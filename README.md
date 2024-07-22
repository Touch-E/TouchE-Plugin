# TouchE-Plugin

## Overview
`TouchEPlugin` is a Kotlin package designed to streamline the integration of touch-based interactions within your android applications.

## SDK supported features:
1. Login to custom server
2. Display list of movies/tv shows available on server
3. Movie detail page
4. Detailed Movie character page
5. Purchase Product from which showed in movie
6. Additional features to like Drag & Drop directly from movie to purchase product
7. Add product to Cart
8. Movie screen includes Vov (Video over video)
9. SDK includes User info screen
10. Order history with user Rating

## Installation

The Package Manager is a tool for automating the distribution of Android Kotlin code and is integrated into the AAR file. Follow the steps below to add the AAR file to your project.

### Step 1: Add the AAR File

Add the AAR file to your project. Place the AAR file in a suitable directory, for example, `libs` or a custom directory like `plugin`.

```gradle
dependencies {
    implementation(files("../plugin/TouchePlugin.aar"))
}
```

### Step 2: Add Dependencies

In your project's `build.gradle` file, include the AAR file and other dependencies:

    implementation("com.google.android.material:material:1.11.0")
   
    implementation("com.intuit.sdp:sdp-android:1.1.0")
    implementation("com.intuit.ssp:ssp-android:1.1.0")
    
    // Rest API
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.1")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.10.0")
    
    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.1")
    implementation("androidx.fragment:fragment-ktx:1.4.1")
    
    // Lifecycle
    implementation("androidx.lifecycle:lifecycle-common:2.4.1")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.4.1")
    
    // Preferences
    implementation("androidx.preference:preference-ktx:1.2.0")
    
    // Glide for image loading
    implementation("com.github.bumptech.glide:glide:4.16.0")
    
    // Amazon AWS SDK
    implementation("com.amazonaws:aws-android-sdk-s3:2.17.1")
    
    // Dot Indicator Libraries
    implementation("ru.tinkoff.scrollingpagerindicator:scrollingpagerindicator:1.0.0")
    implementation("com.tbuonomo:dotsindicator:5.0")
    
    // Vertical Seekbar
    implementation("com.h6ah4i.android.widget.verticalseekbar:verticalseekbar:1.0.0")
    
    // ExoPlayer
    implementation("com.google.android.exoplayer:exoplayer-core:2.17.1")
    implementation("com.google.android.exoplayer:exoplayer-dash:2.17.1")
    implementation("com.google.android.exoplayer:exoplayer-ui:2.17.1")
    implementation("com.google.android.exoplayer:exoplayer-hls:2.17.1")
    implementation("com.google.android.exoplayer:exoplayer-rtsp:2.17.1")


### Step 4: Sync Project with Gradle Files

Make sure to sync your project with Gradle files after adding the dependencies. You can do this by clicking the "Sync Now" button in the notification bar or by selecting `File > Sync Project with Gradle Files`.

## Usage

### Step 5: Validate Server URL and User Token

After adding `TouchEPlugin` First validate the server URL is valid or not and check if the user is already logged in or not in TouchEPlugin using the `validateURLAndToken` method which is given below with example.

```
    private lateinit var login: OpenClass

    val urlString = "https://api-cluster.system.touchetv.com"
    var userToken = "" // If user already login assign save token here.

        login = OpenClass(this)

        login.validateURLAndToken(urlString, userToken) { isURLValid, isTokenValid ->
            Log.d("TouchEPlugin Log", "urlString: $urlString, $userToken")
            Log.d("TouchEPlugin Log", "validateURLAndToken: $isURLValid, $isTokenValid")
        }
```

## User Authentication and Profile Data Handling

To authenticate a user and manage their profile data, you can use the `userAuthentication` method provided by `TouchEPlugin`

## User Login

Login using `userAuthentication` method which nedds a username and password, then returns a result indicating success or failure.

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

## Retrieving Movie List and Cart Data

To fetch movie lists and cart data, you can use the `getMovieDetail` and `getCartDataCount` methods respectively.

### Get Movie list Data

Get movie list using `getMovieDetail` method (example given below)

```
    private lateinit var homeScreen: OpenClass
    private var loginResponse: LoginResponse? = null
    private var data: List<AllContentsResponse>? = null

            // Use this method for get movie list
            homeScreen = OpenClass(this)

            homeScreen.getMovieDetail {
                Log.d("TouchEPlugin Log", "getMovieDetail: $it")
            }
```

### Get Cart Data Count

Get Cart Data Count using `getCartDataCount` method (example given below)

```
    private lateinit var homeScreen: OpenClass
    private var loginResponse: LoginResponse? = null
    private var data: List<AllContentsResponse>? = null

            // Use this method for get Cart Data Count
            homeScreen = OpenClass(this)

            homeScreen.getCartDataCount {
                Log.d("TouchEPlugin Log", "getCartDataCount: $it")
            }

```

## Screen Navigation

`TouchEPlugin` supports navigation to various screens in your app, such as movie details, cart, and profile screens.

## Navigate to Movie Details Screen

```
val intent = Intent(this, MovieDetailActivity::class.java)
intent.putExtra("movieID", movieID)
startActivity(intent)
```

### Navigate to My Cart Screen

```
startActivity(Intent(this, CartActivity::class.java))
```

### Navigate to My Profile Screen

```
startActivity(Intent(this, AccountDetailsActivity::class.java))
```
