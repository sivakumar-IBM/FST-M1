# writing CSV files
import pandas

userdata = {
    "Usernames": ["admin", "Charles", "Deku"],
    "Passwords": ["password", "Charl13", "AllMight"]
}

userdatadf = pandas.DataFrame(userdata)

print(userdatadf)

userdatadf.to_csv('../Activity17data.csv', index=False)
