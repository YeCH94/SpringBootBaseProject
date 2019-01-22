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
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <div class="panel panel-default">
                <div class="panel-body">
                    <div class="text-center">
                        <h3><i class="glyphicon glyphicon-lock" style="font-size:2em;"></i></h3>
                        <h2 class="text-center">Reset password</h2>
                        <div class="panel-body">
                            <#if error ??>
                                <div class="alert alert-danger">
                                    <span> ${error} </span>
                                </div>
                            </#if>

                            <form action=/reset-password method="post">
                                <input type="hidden" name="token" value="${token}"/>
                                <p>
                                    <label for="password" class="sr-only">Password</label>
                                    <input type="password" id="password" name="password" class="form-control" placeholder="Password" required>
                                </p>
                                <p>
                                    <label for="Confirm Password" class="sr-only">Password</label>
                                    <input type="password" id="Confirm_Password" name="confirm_password" class="form-control" placeholder="Confirm_Password" required>
                                </p>
                                <div class="form-group">
                                    <button type="submit" class="btn btn-block btn-success">Reset password</button>
                                </div>
                            </form>

                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    New user? <a href=/register>Register</a>
                </div>
                <div class="col-md-12">
                    Already registered? <a href=/login>Login</a>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>