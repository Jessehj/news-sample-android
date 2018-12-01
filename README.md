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
        >  Note: you can't mix this param with the `sources` param.
        - **country**
        <br>`kr`
        >  Note: you can't mix this param with the `sources` param.
        - **sources**
        - **q** : Keywords or a phrase to search for.
        - **pageSize** (default: 20, maximum: 100)
        - **page**        
            
    - Everything `/v2/everything` :
        - **q**
        - **sources**    
        - **from**
        - **to**
        - **language**
        - **sortBy**
        - **pageSize**
        - **page**
    
    - Sources `/v2/sources` :
        - **category** (default: all categories)
        <br>`business` `entertainment` `general` `health` `science` `sports` `technology`
        - **country** (default: all countries)
        <br>`kr`

### Libraries:

