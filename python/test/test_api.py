

# https://schemathesis.readthedocs.io/en/

#def tst_client2():

from src._man_file import ManFile
import schemathesis

from hypothesis import settings, Phase
from twisted.python.util import println

path_tst = ManFile.dir_path_root()

# path_dir = "/Users/declan/Documents/zone/low/kotlin/open_api/kotlin/src/main/resources/openapi/"
path_yaml = ManFile.file_path_openapi_spec("documentation.yaml")

# path_json = path_dir + "documentation.json"
# with open(path_yaml, 'r') as yaml_file:
#     yaml_data = yaml.safe_load(yaml_file)
#
# with open(path_json, 'w') as json_file:
#     json.dump(yaml_data, json_file)

schema = schemathesis.from_path(path_yaml,  base_url="http://localhost:8080")

@schemathesis.hook
def before_call(context, case):
    println(case.query) # = {"q": "42"}



@settings(max_examples=100)
@schema.parametrize()
def test_api(case):
    case.call_and_validate()
