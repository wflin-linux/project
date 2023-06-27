<template>
  <div class="backg" style="height: 100%">
    <h1 style="margin-top: 0%; line-height: 3">&nbsp;</h1>

    <div class="container w3layouts agileits">
      <div class="login w3layouts agileits">
        <h2>登 录</h2>
        <form action="#" method="post">
          <input
            type="text"
            Name="Userame"
            placeholder="用户名"
            required=""
            v-model="loginForm.username"
          />
          <input
            type="password"
            Name="Password"
            placeholder="密码"
            required=""
            v-model="loginForm.password"
          />
        </form>
        <ul class="tick w3layouts agileits">
          <li>
            <input type="checkbox" id="brand1" value="" />
            <label for="brand1"><span></span>记住我</label>
          </li>
        </ul>
        <div class="send-button w3layouts agileits">
          <form>
            <input type="button" value="登 录" @click="login()" />
          </form>
        </div>
        <div class="clear"></div>
      </div>

      <div class="register w3layouts agileits">
        <h2>注 册</h2>
        <!--			<form action="#" method="post">-->
        <input
          type="text"
          v-model="registerFrom.email"
          id="register_email"
          placeholder="邮箱"
          required=""
        />
        <input
          type="text"
          v-model="registerFrom.realName"
          id="register_realname"
          placeholder="真实姓名"
          required=""
        />
        <input
          type="password"
          v-model="registerFrom.password"
          id="register_password"
          placeholder="密码"
          required=""
        />
        <input
          type="password"
          v-model="registerFrom.repassword"
          id="register_password_ack"
          placeholder="确认密码"
          required=""
        />
        <input
          type="text"
          v-model="registerFrom.code"
          id="register_code"
          placeholder="输入验证码"
          required=""
        />
        <!--			</form>-->
        <div class="send-button w3layouts agileits">
          <!--				<form onsubmit="false">-->
          <input
            type="submit"
            @click="sendCode"
            id="send_code"
            value="发送验证码"
          />
          <input
            type="submit"
            @click="register"
            id="register_button"
            value="免费注册"
          />
          <!--				</form>-->
        </div>
        <div class="clear"></div>
      </div>

      <div class="clear"></div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      // 登陆表单的数据绑定对象
      loginForm: {
        username: "",
        password: "",
      },
      registerFrom: {
        email: "",
        realName: "",
        password: "",
        repassword: "",
        code: "",
      },
      // 表单验证规则对象
      loginFormRules: {
        // 验证用户名是否合法
        username: [
          {
            required: true,
            message: "请输入登陆名称",
            trigger: "blur",
          },
          {
            min: 3,
            max: 10,
            message: "长度在 3 到 10 个字符",
            trigger: "blur",
          },
        ],
        // 验证密码是否合法
        password: [
          {
            required: true,
            message: "请输入登陆密码",
            trigger: "blur",
          },
          {
            min: 6,
            max: 15,
            message: "长度在 6 到 15 个字符",
            trigger: "blur",
          },
        ],
      },
    };
  },
  methods: {
    registerSuccessOpen() {
      this.$alert("注册申请已发送，等待管理员审核，请留意邮箱", "提示", {
        confirmButtonText: "确定",
        callback: (action) => {},
      });
    },
    registerFailOpen() {
      this.$alert("验证码有误", "提示", {
        confirmButtonText: "确定",
        callback: (action) => {},
      });
    },
    login() {
      //      console.log(this.$http)

      this.$http({
        url: "/login/fonts",
        method: "post", //get
        data: {
          email: this.loginForm.username,
          password: this.loginForm.password,
        },
        headers: {
          "Content-Type": "application/json;charset=utf-8",
        },
      }).then((res) => {
        if (res.data.code == 200) {
          //放token
          localStorage.setItem("token", res.data.data.token);
          localStorage.setItem("realName", res.data.data.realName);
          this.$router.push("/index");
          //登陆成功
        }
      });
    },
    sendCode() {
      // console.log("发送验证码")
      this.$http({
        url: "/register/getcode/" + this.registerFrom.email,
        method: "get",
      }).then((res) => {
        console.log(res);
      });
    },
    register() {
      if (this.registerFrom.password != this.registerFrom.repassword) {
        alert("两次密码输入不同");
        return;
      }
      this.$http({
        url: "register",
        method: "post", //get
        data: {
          email: this.registerFrom.email,
          password: this.registerFrom.password,
          realName: this.registerFrom.realName,
          code: this.registerFrom.code,
        },
        headers: {
          "Content-Type": "application/json;charset=utf-8",
        },
      }).then((data) => {
        console.log(data);
        if (data.data.code == 200) {
          // this.registerFrom.email = "";
          // this.registerFrom.password = "";
          // this.registerFrom.repassword = "";
          // this.registerFrom.realName = "";
          // this.registerFrom.code = "";

          this.registerSuccessOpen();
        } else if (data.data.code == 1000) {
          this.registerFailOpen();
        }
      });
    },
    reset() {
      this.loginForm.username = "";
      this.loginForm.password = "";
      // this.$$refs.loginFormRef.resetFields()
    },
  },
};
</script>

<style scoped>
/*-- Reset Code --*/

html,
body,
div,
span,
applet,
object,
iframe,
h1,
h2,
h3,
h4,
h5,
h6,
p,
blockquote,
pre,
a,
abbr,
acronym,
address,
big,
cite,
code,
del,
dfn,
em,
img,
ins,
kbd,
q,
s,
samp,
small,
strike,
strong,
sub,
sup,
tt,
var,
b,
u,
i,
dl,
dt,
dd,
ol,
nav ul,
nav li,
fieldset,
form,
label,
legend,
table,
caption,
tbody,
tfoot,
thead,
tr,
th,
td,
article,
aside,
canvas,
details,
embed,
figure,
figcaption,
footer,
header,
hgroup,
menu,
nav,
output,
ruby,
section,
summary,
time,
mark,
audio,
video {
  margin: 0;
  padding: 0;
  border: 0;
  font-size: 100%;
  font: inherit;
  vertical-align: baseline;
}

article,
aside,
details,
figcaption,
figure,
footer,
header,
hgroup,
menu,
nav,
section {
  display: block;
}

ol,
ul {
  list-style: none;
  padding: 0;
  margin: 0;
}

blockquote,
q {
  quotes: none;
}

blockquote:before,
blockquote:after,
q:before,
q:after {
  content: "";
  content: none;
}

table {
  border-collapse: collapse;
  border-spacing: 0;
}

a {
  text-decoration: none;
}

.txt-rt {
  text-align: right;
}

.txt-lt {
  text-align: left;
}

.txt-center {
  text-align: center;
}

.float-rt {
  float: right;
}

.float-lt {
  float: left;
}

.clear {
  clear: both;
}

.pos-relative {
  position: relative;
}

.pos-absolute {
  position: absolute;
}

.vertical-base {
  vertical-align: baseline;
}

.vertical-top {
  vertical-align: top;
}

.underline {
  padding-bottom: 3px;
  border-bottom: 1px solid #eee;
}

nav.vertical ul li {
  display: block;
}

nav.horizontal ul li {
  display: inline-block;
}

img {
  max-width: 100%;
}

/*-- Reset Code --*/

/*-- Index-Page-Styling --*/

body {
  font-family: "Roboto", sans-serif;
  text-align: center;
  background: url("../assets/img/front-login-background.jpg");
  background-repeat: no-repeat;
  background-attachment: fixed;
  background-position: center;
  background-size: cover;
  -webkit-background-size: cover;
  -moz-background-size: cover;
  -o-background-size: cover;
}

.backg {
  font-family: "Roboto", sans-serif;
  text-align: center;
  background: url("../assets/img/front-login-background.jpg");
  background-repeat: no-repeat;
  background-attachment: fixed;
  background-position: center;
  background-size: cover;
  -webkit-background-size: cover;
  -moz-background-size: cover;
  -o-background-size: cover;
}

body a {
  transition: 0.5s all;
  -webkit-transition: 0.5s all;
  -moz-transition: 0.5s all;
  -o-transition: 0.5s all;
  -ms-transition: 0.5s all;
  text-decoration: none;
}

h1 {
  font-size: 45px;
  font-weight: 500;
  letter-spacing: 4px;
  margin: 60px 0;
  color: #ffffff;
}

.container {
  width: 50%;
  margin: 0 auto;
  padding: 40px;
  background-color: rgba(10, 10, 10, 0.77);
  border: 2px ridge rgba(238, 238, 238, 0.13);
  border-radius: 5px;
  -moz-box-shadow: 0 -5px 10px 1px rgba(16, 16, 16, 0.57);
  -webkit-box-shadow: 0 -5px 10px 1px rgba(16, 16, 16, 0.57);
  box-shadow: 0 -5px 10px 1px rgba(16, 16, 16, 0.57);
  border-bottom: none;
  border-bottom-left-radius: initial;
  border-bottom-right-radius: initial;
}

.login {
  width: 45%;
  float: left;
  text-align: left;
  padding-right: 40px;
}

::-webkit-input-placeholder {
  color: #ccc;
}

:-moz-placeholder {
  color: #ccc;
}

::-moz-placeholder {
  color: #ccc;
}

:-ms-input-placeholder {
  color: #ccc;
}

h2 {
  font-size: 35px;
  text-align: left;
  color: #fff;
  font-weight: 100;
  margin-bottom: 20px;
}

ul.tick {
  float: left;
  list-style: none;
  display: inline-block;
  width: 100%;
  margin-bottom: 20px;
}

ul.tick li input[type="checkbox"] {
  display: none;
}

ul.tick li input[type="checkbox"] + label {
  position: relative;
  padding-left: 30px;
  border: #f0f8ff;
  display: inline-block;
  font-size: 13px;
  color: #eee;
}

ul.tick li input[type="checkbox"] + label span:first-child {
  width: 17px;
  height: 17px;
  display: inline-block;
  border: 1px solid #eee;
  position: absolute;
  top: -3px;
  left: 0;
  bottom: 4px;
}

ul.tick li input[type="checkbox"]:checked + label span:first-child:before {
  content: "";
  /* background: url(../img/front-login-tick.png)no-repeat; */
  position: absolute;
  left: 3px;
  top: 3px;
  font-size: 10px;
  width: 10px;
  height: 10px;
}

.social-icons {
  margin-top: 20px;
  text-align: center;
}

.social-icons p {
  color: #ccc;
  margin-bottom: 15px;
}

.social-icons ul li {
  float: left;
  width: 32.9%;
}

.copyrights {
  text-indent: -9999px;
  height: 0;
  line-height: 0;
  font-size: 0;
  overflow: hidden;
}

li.twt {
  margin: 0 2px;
}

.social-icons ul li a {
  display: block;
}

.social-icons ul li a span {
  vertical-align: middle;
  color: #fff;
  font-size: 15px;
}

.social-icons ul li span.icons {
  /* background: url("../images/i1.png") no-repeat center; */
  width: 30px;
  height: 30px;
  display: inline-block;
}

.social-icons ul li.weixin span.icons {
  /* background: url("../images/weixin.png") no-repeat; */
  background-size: 100%;
}

.social-icons ul li.qq span.icons {
  /* background: url("../images/QQ.png") no-repeat; */
  background-size: 100%;
}

.social-icons ul li.weibo span.icons {
  /*--w3layouts--*/
  /*--agileits--*/
  /* background: url("../images/weibo.png") no-repeat; */
  background-size: 100%;
}

.social-icons ul li a:hover span.icons {
  transform: rotatey(360deg);
  -webkit-transform: rotatey(360deg);
  -o-transform: rotatey(360deg);
  -moz-transform: rotatey(360deg);
  -ms-transform: rotatey(360deg);
  transition: 0.5s all;
}

input[type="text"],
input[type="password"] {
  width: 93.4%;
  margin-bottom: 20px;
  padding: 10px;
  float: left;
  background-color: transparent;
  border: none;
  font-size: 15px;
  border-bottom: 1px solid rgba(238, 238, 238, 0.41);
  outline: none;
  color: #fff;
}

.send-button {
  margin-bottom: 20px;
}

.send-button input[type="submit"],
.send-button input[type="button"] {
  width: 60%;
  padding: 10px 0;
  font-size: 16px;
  font-weight: 100;
  background-color: transparent;
  color: #ccc;
  border: 1px solid rgba(238, 238, 238, 0.41);
  border-width: thin;
  cursor: pointer;
  outline: none;
  transition: 0.5s all;
  -webkit-transition: 0.5s all;
  -moz-transition: 0.5s all;
  -o-transition: 0.5s all;
  -ms-transition: 0.5s all;
  text-decoration: none;
}

/*--w3layouts--*/
/*--agileits--*/
.send-button input[type="submit"]:hover,
.send-button input[type="button"]:hover {
  background-color: #000;
  border: 1px solid #fff;
  color: #fff;
  transition: 0.5s all;
  -webkit-transition: 0.5s all;
  -moz-transition: 0.5s all;
  -o-transition: 0.5s all;
  -ms-transition: 0.5s all;
  text-decoration: none;
}

.login a {
  color: #ccc;
}

.login a:hover {
  color: #fff;
}

.register {
  width: 44%;
  float: left;
  border-left: 1px solid #ddd;
  padding-left: 40px;
  text-align: left;
}

.register p {
  color: #ccc;
  margin-bottom: 10px;
}

.register p a {
  color: #ccc;
}

.register p a:hover {
  color: #fff;
}

.footer {
  text-align: center;
  margin: 100px 20px 20px;
}

.footer p {
  color: #ccc;
  line-height: 25px;
  /*--w3layouts--*/
  /*--agileits--*/
}

.footer a {
  color: #ccc;
  text-decoration: none;
}

.footer a:hover {
  color: #fff;
}

/*-- Index-Page-Styling --*/

/*--- Responsive Code ---*/

@media screen and (max-width: 1920px) {
  h1 {
    margin: 100px 0;
  }
}

@media screen and (max-width: 1600px) {
  h1 {
    margin: 60px 0;
  }
}

@media screen and (max-width: 1440px) {
  .container {
    width: 53%;
  }
}

@media screen and (max-width: 1366px) {
  h1 {
    margin: 50px 0;
  }

  .container {
    width: 57%;
  }

  /*--w3layouts--*/
  /*--agileits--*/
}

@media screen and (max-width: 1280px) {
  .container {
    width: 60%;
  }
}

@media screen and (max-width: 1024px) {
  .container {
    width: 76%;
  }
}

@media screen and (max-width: 966px) {
  .container {
    width: 80%;
  }

  .footer {
    margin: 50px 20px 20px;
  }
}

@media screen and (max-width: 853px) {
  .login {
    padding-right: 36px;
  }

  .register {
    padding-left: 36px;
  }

  .social-icons ul li {
    width: 32.8%;
  }

  .register p {
    font-size: 14px;
  }
}

@media screen and (max-width: 800px) {
  h1 {
    font-size: 43px;
  }

  .login {
    padding-right: 35px;
  }

  .register {
    padding-left: 34px;
  }

  .social-icons ul li span.icons {
    width: 28px;
  }
}

@media screen and (max-width: 768px) {
  .container {
    width: 85%;
  }

  .register p {
    font-size: 13.8px;
  }
}

@media screen and (max-width: 736px) {
  h1 {
    font-size: 40px;
  }

  .container {
    width: 87%;
    padding: 35px;
  }

  .login {
    padding-right: 34px;
  }

  .register {
    padding-left: 33px;
  }

  .social-icons ul li a span {
    font-size: 14px;
  }

  .register p {
    font-size: 13.2px;
  }
}

@media screen and (max-width: 667px) {
  h1 {
    font-size: 35px;
    letter-spacing: 3px;
  }

  .container {
    width: 55%;
    padding: 50px;
  }

  .login {
    padding: 0;
    width: 100%;
    margin-bottom: 50px;
  }

  .register {
    padding: 40px 0 0 0;
    width: 100%;
    border: none;
    border-top: 1px solid #ddd;
  }
}

@media screen and (max-width: 603px) {
  h1 {
    letter-spacing: 2px;
  }
}

@media screen and (max-width: 568px) {
  h1 {
    letter-spacing: 1px;
    margin: 30px 0;
  }

  .container {
    width: 60%;
    padding: 40px;
  }
}

@media screen and (max-width: 533px) {
  h1 {
    font-size: 32px;
  }
}

@media screen and (max-width: 480px) {
  .container {
    width: 70%;
    padding: 35px;
  }

  .send-button input[type="submit"] {
    width: 50%;
    padding: 8px 0;
  }
}

@media screen and (max-width: 414px) {
  h1 {
    font-size: 30px;
  }

  .container {
    width: 75%;
    padding: 25px;
  }

  .login {
    margin-bottom: 30px;
  }

  h2 {
    font-size: 30px;
    margin-bottom: 15px;
  }

  .register {
    padding: 30px 0 0 0;
  }

  .footer p {
    font-size: 14px;
  }
}

@media screen and (max-width: 384px) {
  h1 {
    letter-spacing: 0px;
  }
}

@media screen and (max-width: 375px) {
  .social-icons ul li span.icons {
    width: 27px;
  }

  .register p {
    font-size: 13px;
    line-height: 23px;
  }
}

@media screen and (max-width: 360px) {
  .container {
    width: 80%;
    padding: 20px;
  }
}

@media screen and (max-width: 320px) {
  .social-icons ul li {
    width: 32.7%;
  }

  .social-icons ul li span.icons {
    width: 25px;
  }

  .social-icons ul li a span {
    font-size: 12.4px;
  }
}

/*--- Responsive Code ---*/
</style>
