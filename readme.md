## Overview
A minimal Kotlin (Ktor) http sever and python client.
The client reads the openapi spec generated automatically from the server code via the intellij plugin.
It then peforms a smoke test, verifying that all endpoints documented in the spec exist and call succeeds.
The results are collected in a pandas dataframe to facilitate  filtering, sorting

Note: 
* Intellij also provides a graphical (swagger ui) view of the endpoints in the spec
* The documentation can also be generated via a gradle task  (but this requires modification to build.gradle.kts)


### Usage
* After editing any Routes, refresh the openapi spec (documentation.yaml)
* (Re)Run server
* Run the python client


sample Output:

    -----------------------------
    Failed 2 of 3 tests
    -----------------------------
    |    | Pass   | Method   | Path      |   Status_Code | Response_Text   |
    |---:|:-------|:---------|:----------|--------------:|:----------------|
    |  1 | False  | post     | /customer |           415 |                 |
    |  2 | False  | get      | /qstr     |           404 |                 |
    |  0 | True   | get      | /         |           200 | Hello null      |
