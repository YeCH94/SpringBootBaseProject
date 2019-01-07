<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>Please sign in</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
    <link href="https://getbootstrap.com/docs/4.0/examples/signin/signin.css" rel="stylesheet" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <form class="form-signin" action="/login" method="post">
        <h2 class="form-signin-heading">Please sign in</h2>
        <p>
            <label for="email" class="sr-only">User E-Mail</label>
            <input type="email" id="email" name="email" class="form-control" placeholder="E-mail">
        </p>
        <p>
            <label for="password" class="sr-only">Password</label>
            <input type="password" id="password" name="password" class="form-control" placeholder="Password">
        </p>

        <input name="_csrf" type="hidden" value="0e0b074d-e0ce-4fe2-9cc6-b9f349458e3c">
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
    </form>
    <#if securityException ??>
        <p><${"securityException"}/p>
    </#if>

</div>

</body>
</html>