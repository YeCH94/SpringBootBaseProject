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
                        <h2 class="text-center">Forgot Password?</h2>
                        <p>Enter your e-mail address and we'll send you a link to reset your password.</p>
                        <div class="panel-body">
                            <form action="/forgot" method="post">
                                <div class="form-group">
                                    <div class="input-group">
                                        <input id="email" name="email" class="form-control" placeholder="email address"/>
                                    </div>

                                </div>
                                <div class="form-group">
                                    <button type="submit" class="btn btn-success btn-block">Reset Password</button>
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
                    Already registered? <a href="/login">Login</a>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>