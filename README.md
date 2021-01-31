# Giphy Search API

## Introduction
Giphy Search API allows to search for up to three `gifs` with multiple query terms. 

More on how to run this project [here link](Instructions.md).


###Search
Searches for one or many gifs returning up to three results per match.

*URL*: `/api/{version}/giphy/query`

*Method*: `GET`

*Auth required*: `NO`


####Success Response

*Code*: `200 OK`


####Example

#####HTTP Request

```bash
curl --request GET 'localhost:8080/api/v1/giphy/query?searchTerm=ronaldinho&searchTerm=dinho'
```

#####HTTP Response

```json
{
    "data": [
        {
            "search_term": "ronaldinho",
            "gifs": [
                {
                    "url": "https://giphy.com/gifs/nikefootball-football-soccer-1Ai6ZCMr24Xn3QRP4v",
                    "gif_id": "1Ai6ZCMr24Xn3QRP4v"
                },
                {
                    "url": "https://giphy.com/gifs/nss-sports-soccer-milan-3ov9kae2vVoWjkpMje",
                    "gif_id": "3ov9kae2vVoWjkpMje"
                },
                {
                    "url": "https://giphy.com/gifs/fcbarcelona-goal-ronaldinho-FEpLruiKc7udq",
                    "gif_id": "FEpLruiKc7udq"
                }
            ]
        },
        {
            "search_term": "dinho",
            "gifs": [
                {
                    "url": "https://giphy.com/gifs/nikefootball-football-soccer-1Ai6ZCMr24Xn3QRP4v",
                    "gif_id": "1Ai6ZCMr24Xn3QRP4v"
                },
                {
                    "url": "https://giphy.com/gifs/g-dinho-guilherme-prates-4IWwwIF2582oo",
                    "gif_id": "4IWwwIF2582oo"
                },
                {
                    "url": "https://giphy.com/gifs/13-malhacao-lia-fhsjFurz3nh7O",
                    "gif_id": "fhsjFurz3nh7O"
                }
            ]
        }
    ]
}
```