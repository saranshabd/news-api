# News API

### Description

The API has just 1 endpoint which returns a list of top news sources names (_sorted_) based upon given parameters.

### Data Source

[News API](http://news-api.org)

### Available parameters

-   `k`

    **Required**

    _Maximum number of records to return_

-   `country`

    _Country code of News Sources._

    #### Available Country Codes:

    `be` `bg` `br` `ca` `ch` `cn` `co` `cu` `cz` `de` `eg` `fr` `gb` `gr` `hk` `hu` `id` `ie` `il` `in` `it` `jp` `kr` `lt` `lv` `ma` `mx` `my` `ng` `nl` `no` `nz` `ph` `pl` `pt` `ro` `rs` `ru` `sa` `se` `sg` `si` `sk` `th` `tr` `tw` `ua` `us` `ve` `za`

    **Default** - All

*   `category`

    Category of news sources

    #### Available Categories:

    `business` `entertainment` `general` `health` `science` `sports` `technology`

    **Default** - All

### Steps for Local Deployment

#### Pre-requisites

-   [Docker](https://www.docker.com)
-   [News API](http://news-api.org) Authentication Credentials

#### Steps

-   Create a new file `src/main/resources/application-local.properties` with following content.

    ```properties
    server.news.api.url=http://newsapi.org
    server.news.api.key=<api-key>
    ```

-   Run following command to build JAR file.

    ```bash
    mvn clean install
    ```

-   Run following command to build Docker image and run it as a container.

    ```bash
    docker-compose up --build
    ```

#### Note

_This project was created to be submitted as an assignment, and is not one of my personal projects._
