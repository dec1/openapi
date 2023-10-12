

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

    case.headers["Authorization"] = "Bearer eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJ3YkJTdC1ncGlDQ2FZc2FXUWVXODF4OXB1bzBjSTFvWm5JRUlWZm9VWEtjIn0.eyJleHAiOjE2OTcxMTI4MTksImlhdCI6MTY5NzExMDQxOSwianRpIjoiYjk3MGU5MzEtNWI5Ni00MjY1LTg2OTYtODgyZjc5ZGZjOTVkIiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo0MDAxL3JlYWxtcy9jYWx2aW4iLCJhdWQiOiJyZWFsbS1tYW5hZ2VtZW50Iiwic3ViIjoiYmZhOWQwMzYtMTA1Zi00M2E0LWE3NGEtYjc1NTEwZDJlY2JlIiwidHlwIjoiQmVhcmVyIiwiYXpwIjoiY2FsdmluIiwic2Vzc2lvbl9zdGF0ZSI6IjJjN2ExZDczLTgxMWYtNGRhZS1iZDQ4LWUxNjkyNmU0NGUzZSIsImFjciI6IjEiLCJhbGxvd2VkLW9yaWdpbnMiOlsiKiJdLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsiZGVmYXVsdC1yb2xlcy1jYWx2aW4iLCJjb21wYW55X21hbmFnZXIiLCJvZmZsaW5lX2FjY2VzcyIsInVtYV9hdXRob3JpemF0aW9uIl19LCJyZXNvdXJjZV9hY2Nlc3MiOnsicmVhbG0tbWFuYWdlbWVudCI6eyJyb2xlcyI6WyJtYW5hZ2UtdXNlcnMiLCJ2aWV3LXVzZXJzIiwicXVlcnktZ3JvdXBzIiwicXVlcnktdXNlcnMiXX0sImNhbHZpbiI6eyJyb2xlcyI6WyJjb21wYW55OnJlYWQiLCJjb21wYW55OndyaXRlIiwiYWN0aXZpdHk6d3JpdGUiLCJjb21wYW55OmNyZWF0ZSIsImFkbWluOioiXX19LCJzY29wZSI6ImVtYWlsIHByb2ZpbGUiLCJzaWQiOiIyYzdhMWQ3My04MTFmLTRkYWUtYmQ0OC1lMTY5MjZlNDRlM2UiLCJjb21wYW5pZXMiOlsiNjUyNjYzM2M2M2MyNGIyMDJjZDEyNzYzIl0sImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJwZXJtaXNzaW9ucyI6WyJtYW5hZ2UtdXNlcnMiLCJ2aWV3LXVzZXJzIiwicXVlcnktZ3JvdXBzIiwicXVlcnktdXNlcnMiLCJjb21wYW55OnJlYWQiLCJjb21wYW55OndyaXRlIiwiYWN0aXZpdHk6d3JpdGUiLCJjb21wYW55OmNyZWF0ZSIsImFkbWluOioiXSwibmFtZSI6ImRlYyBtb3IiLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiJkZWNsYW4iLCJnaXZlbl9uYW1lIjoiZGVjIiwiZmFtaWx5X25hbWUiOiJtb3IiLCJlbWFpbCI6ImRlY2xhbkB0c3QuY29tIn0.ZCWdSwOWQNMuQFKwnlOsAVnwgSN54IJSXrq3U0wN5JAFCkO30Ckw2TLCaBjXvwPn9s6vrmCtQIduL5aHmOucE9muiKE7vtDwepS3gJg-fB99VGEeKHybmIjp2EsjOJ_hk6vkZyi52KUJPkS_NfvjnATuBi1oKVNu2Na8Sq3RiTZMofYAJRnMO3Mucksrw7QmVLS-V83dgbdenk0O72Vxsrefzoj1vTEs8kg5BICuFT8mR3X33S9Yqy-BysDLHp6PpdSaDBdYZYV6BtrXZK7zc-rGbPbts6jwnPRl6sRv3hiEIJNygYzkbefeLPHkGqiqm9k-swpUXn-EENoyL9jJWw"

    if case.query is not None:
        print(f"Query Parameters: {case.query}")
    else:
        print("No query parameters for this case.")

    if case.body is not None:
        print(f"Request Body: {case.body}")
    else:
        print("No request body for this case.")

# endpoints_to_test = [("/companies", "GET")]
my_endpoint = "/companies"
my_method = "POST"
                 #    ("/endpoint2", "POST")]
#ENDPOINTS_AND_METHODS_TO_TEST = endpoints_to_test

#for endpoint, method in ENDPOINTS_AND_METHODS_TO_TEST:
@settings(max_examples=100, derandomize=True)
@schema.parametrize(endpoint=my_endpoint, method=my_method)
def test_api(case):
    case.call_and_validate()

# @settings(max_examples=25, derandomize=True)
# @schema.parametrize()
# def test_api(case):
#     case.call_and_validate()
