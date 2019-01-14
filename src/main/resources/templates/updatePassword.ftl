<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Please sign in</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
    <link href="https://getbootstrap.com/docs/4.0/examples/signin/signin.css" rel="stylesheet" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <form class="form-signin" name="form" action="/updatePassword" method="post">
        <input type="email" name="email" value="${email}"/>
        <label for="password" class="sr-only">Password</label>
        <input type="password" id="password" name="password" class="form-control" placeholder="새로운 비밀번호를 입력해주세요."
               required>
        <label for="confirm_password" class="sr-only">Password Confirm</label>
        <input type="password" id="confirm_password" name="confirm_password" class="form-control" placeholder="비밀번호를 다시 입력해주세요." required>
        <input name="_csrf" type="hidden" value="0e0b074d-e0ce-4fe2-9cc6-b9f349458e3c">
        <button class="btn btn-lg btn-primary btn-block" type="submit">확인</button>
    </form>

    <#if error ??>
        <p>${error.errMsg}</p>
    </#if>
</div>

</body>
</html>