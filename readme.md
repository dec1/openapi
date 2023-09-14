Make sure Kotr server is running.

The python client reads the openapi spec (documenation.yaml) file and runs the tests.,
to make sure each endpoint is returning success.



Sample Output:

-----------------------------
Failed 2 of 3 tests
-----------------------------
|    | Pass   | Method   | Path      |   Status_Code | Response_Text   |
|---:|:-------|:---------|:----------|--------------:|:----------------|
|  1 | False  | post     | /customer |           415 |                 |
|  2 | False  | get      | /qstr     |           404 |                 |
|  0 | True   | get      | /         |           200 | Hello null      |
