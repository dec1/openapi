## Overview
A minimal Kotlin (Ktor) http sever and python test client.
The server uses [ktor-swagger-ui](https://github.com/SMILEY4/ktor-swagger-ui) to document the api
within the [kotlin (routing) code](./kotlin/src/main/kotlin/com/example/plugins/Routing.kt). This allows it to serve both a [swagger-ui](./screenshot/swagger-ui.png) of the API, and 
a corresponding [openapi spec](./screenshot/openapi.png), as additional endpoints.  

The python client uses [schemathesis](https://schemathesis.readthedocs.io/en/) and property-based testing to query the api,
trying to provoke a mismatch between the published openapi sepc and the actual running server.
This helps verify that the server is adhering to the openapi spec it publishes.


### Usage
* Run the Ktor server
```
.....
2023-10-05 17:52:11.162 [main] DEBUG c.g.v.j.g.i.SchemaGenerationContextImpl - directly applying configured custom inline type for java.lang.String
2023-10-05 17:52:11.163 [main] DEBUG c.g.v.j.g.i.SchemaGenerationContextImpl - storing configured custom inline type for java.lang.String as definition (since it is the main schema "#")
2023-10-05 17:52:11.453 [DefaultDispatcher-worker-1] INFO  ktor.application - 
Responding at http://localhost:8080
```

* Open http://localhost:8080 in [Browser](./screenshot/swagger-ui.png)
* Run the python client either in 
    [pycharm](./screenshot/pycharm.png)

or on the command line

   ```python> ./prj/venv/bin/python -m  pytest -v [--capture=no] test/```

    ========================================================================================== test session starts ===========================================================================================
    platform darwin -- Python 3.10.7, pytest-7.4.2, pluggy-1.3.0 -- /Users/declan/Documents/zone/low/kotlin/open_api/python/prj/venv/bin/python
    cachedir: .pytest_cache
    hypothesis profile 'default' -> database=DirectoryBasedExampleDatabase('/Users/declan/Documents/zone/low/kotlin/open_api/python/.hypothesis/examples')
    rootdir: /Users/declan/Documents/zone/low/kotlin/open_api/python
    plugins: anyio-4.0.0, schemathesis-3.19.7, subtests-0.7.0, hypothesis-6.86.1
    collected 6 items                                                                                                                                                                                        

    test/test__api.py::test_api[GET /hello] PASSED                                                                                                                                                     [ 16%]
    test/test__api.py::test_api[POST /echo/{color}] PASSED                                                                                                                                             [ 33%]
    test/test__api.py::test_api[POST /math/{operation}] PASSED                                                                                                                                         [ 50%]
    test/test__api.py::test_api[GET /qstr] PASSED                                                                                                                                                      [ 66%]
    test/test__api.py::test_api[POST /customers] PASSED                                                                                                                                                [ 83%]
    test/test__api.py::test_api[POST /customer] PASSED  
