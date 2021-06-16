<!DOCTYPE html>
<html>
<head>
    <meta charset="EUC-KR">
    <title>hi</title>
    <link rel="stylesheet" href="jqcloud.css">
    <script src="jquery-3.3.1.min.js" charset="utf-8"></script>
    <script src="jqcloud.js" charset="utf-8"></script>

    <script>
        var words = [
            {text: "Lorem", weight: 13},
            {text: "Ipsum", weight: 10.5},
            {text: "Dolor", weight: 9.4},
            {text: "Sit", weight: 8},
            {text: "Amet", weight: 6.2},
            {text: "Consectetur", weight: 5},
            {text: "Adipiscing", weight: 5} ];

        $(function() {
            $("#keywords").jQCloud(words, {
                width: 500,
                height: 350
            });
        });
    </script>
</head>
<body>
<div id="keywords"></div>
</body>
</html>
