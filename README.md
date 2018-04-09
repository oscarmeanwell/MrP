# MrP

Server JSON structure looks like this

```
{
    "temp": "10",
    "humidity": null,
    "datetime": "2018-04-09 17:04:19"
}
```

To get Data from the server:
```
HashMap<String, String> data = new GetServerData().GetServerDataNow();
System.out.println("TEMP IS: " + data.get("temp"));
```
The hashmap data then contains the keys: "temp", "humidity", & "datetime".
