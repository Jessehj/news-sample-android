# news-sample-android

### News API Description
- Root Url: 
    <br> `https://newsapi.org/`
    
- Authentication
    <br>[[Get API key]](https://newsapi.org)
    <br>My API key: `c44e006dbc734ae78ae222a26f177cb9`
    > Provide api key via the `X-Api-Key` HTTP header.
    
- Endpoints:
    - Top headlines `/v2/top-headlines` :
        - **category**
        <br>`business` `entertainment` `general` `health` `science` `sports` `technology`     
        - **country**
        <br>`kr`
        - **q** : Keywords or a phrase to search for.
        - **pageSize** (default: 20, maximum: 100)
        - **page**        
            
    - Everything `/v2/everything` :
        - **q**
        - **sources**    
        - **pageSize**
        - **page**
    
    - Sources `/v2/sources` :
        - **category** (default: all categories)
        <br>`business` `entertainment` `general` `health` `science` `sports` `technology`
        - **country** (default: all countries)
        <br>`kr`

### Project architecture:
- [VIPER](https://jessehj.github.io/2018-06-15/Clean-Architecture-VIPER-1) Architecture : Inspired from Clean Architecture from Uncle Bob.

### Libraries:
- Android support libraries
    - RecyclerView
    - CardView
    - ConstraintLayout
    - ...
- Kotlin Anko libraries
    - anko-commons
    - anko-design
    > Toolbox for kotlin, simple toast & snackbar & sintent helper,  
- Retrofit
    - retrofit2
    - converter-gson
    - (okhttp3) logging-interceptor
    > Http client libraries
- Lottie
> Simple use animation for loading progress
- Picasso : 
> Display images with url or local path
- Timber
> Display log
- Robolectric
> Unit test