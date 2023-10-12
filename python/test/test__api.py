

# https://schemathesis.readthedocs.io/en/

#def tst_client2():

from src._man_file import ManFile
import schemathesis

from hypothesis import settings
from twisted.python.util import println

path_tst = ManFile.dir_path_root()

# path_dir = "/Users/declan/Documents/zone/low/kotlin/open_api/kotlin/src/main/resources/openapi/"
path_yaml = ManFile.file_path_openapi_spec("documentation.yaml")
path_json = ManFile.file_path_openapi_spec("api.json")
# path_json = path_dir + "documentation.json"
# with open(path_yaml, 'r') as yaml_file:
#     yaml_data = yaml.safe_load(yaml_file)
#
# with open(path_json, 'w') as json_file:
#     json.dump(yaml_data, json_file)

# schema = schemathesis.from_path(path_json,  base_url="http://localhost:8080/swagger-ui")
schema = schemathesis.from_uri("http://localhost:8080/swagger-ui/api.json", base_url="http://localhost:8080/")

#@schemathesis.hook
#def before_call(context, case):
#   print(case.query) # = {"q": "42"}

@schemathesis.hook
def before_call(context, case):
    #case.call_and_validate(headers={"Authorization": "Bearer MY_TOKEN"})

    if case.query is not None:
        print(f"Query Parameters: {case.query}")
    else:
        print("No query parameters for this case.")

    if case.body is not None:
        print(f"Request Body: {case.body}")
    else:
        print("No request body for this case.")

endpoints_to_test = [("/hello", "GET"), ]
                 #    ("/endpoint2", "POST")]
ENDPOINTS_AND_METHODS_TO_TEST = endpoints_to_test

for endpoint, method in ENDPOINTS_AND_METHODS_TO_TEST:
    @settings(max_examples=100, derandomize=True)
    @schema.parametrize(endpoint=endpoint, method=method)
    def test_api(case):
        case.call_and_validate()

# @settings(max_examples=25, derandomize=True)
# @schema.parametrize()
# def test_api(case):
#     case.call_and_validate()
