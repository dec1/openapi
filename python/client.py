import yaml
import requests
import pandas as pd
import os




# ---------------------------------------
def tst_client():
    url_base = "http://localhost:8080"

    current_script_dir = os.path.dirname(os.path.abspath(__file__))
    filepath_spec = os.path.join(current_script_dir, '../', 'kotlin/src/main/resources/openapi/documentation.yaml')
    # "/Users/declan/Documents/zone/low/kotlin/ktor/src/main/resources/openapi/documentation.yaml"
    # Load and parse OpenAPI spec

    with open(filepath_spec, 'r') as f:
        spec_dict = yaml.safe_load(f)


    df = pd.DataFrame()
    # Loop through API paths and methods
    for path, path_item in spec_dict['paths'].items():
        for method, operation in path_item.items():
            # print(f"calling: {method} {path}")
            url = f"{url_base}{path}"

            res = requests.request(method, url)  # Add (operation.)parameters as needed
            # eg for get request, operation.parameters is a list of dicts eg . could discover (name, type) of each
            passed  = res.status_code == 200

            row = pd.DataFrame({'Pass': passed, 'Method': method, 'Path': path, 'Status_Code': res.status_code,
                                'Response_Text': res.text[:100]}, index=[0])  # First 100 characters of response; customize as needed
            df = pd.concat([df, row], ignore_index=True)



        # df_fail = df[df['Pass'] == False].copy()

    df = df.sort_values(by='Pass', ascending=True)

    num_failed = len(df[df['Pass'] == False])
    print("-----------------------------")
    print(f"Failed {num_failed} of {len(df)} tests")
    print("-----------------------------")
    print(df.to_markdown())

# ---------------------------------------
tst_client()
