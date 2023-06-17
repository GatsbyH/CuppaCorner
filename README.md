1.1系统功能概述
本次项目主要实现了短信验证功能，文字图片的展示功能。通过调用后端接口来获得数据，用XUI组件和瀑布流布局, WebView等来搭建每个界面，可通过后台管理系统对每个界面的数据进行管理，后端接口使用Spring Security, Json Web Token做了防护，只有通过短信验证，才可以访问后端接口。应用包括十六大界面：
登录界面：包含短信登陆
动态界面：包含轮播图，九宫格菜单和资讯界面
轮播图：展示图片，点击可弹出Toast
九宫格菜单：点击可跳转到对应界面
小贴士：一个弹窗
资讯：用XUI组件对图片文字进行了展示，点击可跳转至详情页
美食界面：用瀑布流对图片文字进行了展示，点击可跳转至详情页 
电影演出：跳转至淘票票
购物：跳转至淘宝
带专：跳转至山东电子职业技术学院官网
外卖：跳转至美团外卖
动漫：跳转至动漫网站
音乐：跳转至网易云音乐
音乐剧界面：用瀑布流对图片文字进行了展示，点击可跳转至详情页 
趋势界面：一个WebView，展示了微博热搜界面
我的：可进入设置，关于等菜单，执行退出登录操作，查看隐私条款等操作
1.2相关技术概述
Android前端：
1.容器类布局：瀑布流布局，LinearLayout使用
2.简单控件的绘制与使用：
TextView：显示文本内容的控件
ImageView：设置显示图片的控件
Button：按钮控件
EditText：可接受用户输入的控件
3.复杂控件的绘制与使用：
ListView：显示列表数据控件，使用适配器填充其内部内容，其中适配器则是采用BaseAdapter
StaggeredGridLayoutManager：是Android中的一个布局管理器，用于在RecyclerView中实现瀑布流布局。与传统的网格布局不同，瀑布流布局的每个单元格的宽度可以不同，可以实现更加灵活的布局效果。StaggeredGridLayoutManager可以根据项目的位置和大小自动调整布局，使得每个单元格的大小和位置都不同。
RecyclerView：RecyclerView是Android系统中的一个Widget，用于在屏幕上显示类似列表或网格的数据集合。相比于ListView，RecyclerView提供了更加灵活和高效的布局管理器，以及更好的性能和可扩展性。
WebView：是Android系统中的一个Widget，用于在应用程序中显示Web内容。WebView可以加载Web页面、HTML文件、图像、视频、音频等多种类型的内容，并支持与JavaScript的交互。
GridView：显示网格数据控件，与ListView使用方法雷同
ViewPager：可左右滑动的包含多个view或者Fragment的控件，其中适配器采用FragmentStatePagerAdapter实现左右滑屏
CardViw：用于ListView每一个item的容器类布局，可制作卡片式效果。
4.简单第三方框架使用
XUI 一个简洁而优雅的Android原生UI框架，解放你的双手！
XUtil 一个方便实用的Android工具类库！
XAOP 一个轻量级的AOP(Android)应用框架。囊括了最实用的AOP应用。
XPage 一个非常方便的fragment页面框架。
XUpdate 一个轻量级、高可用性的Android版本更新框架。
XHttp2 一个功能强悍的网络请求库，使用RxJava2 + Retrofit2 + OKHttp组合进行封装。
AndroidAutoSize 优秀的屏幕适配方案
ViewBinding Google推荐的视图绑定工具
vlayout 阿里巴巴开源淘宝多布局组件
MMKV 腾讯开源键值对存储
leakcanary 内存泄漏检测

安卓后台管理系统:
前端采用Vue、Element UI。
后端采用Spring Boot、Spring Security、Redis & Jwt。
权限认证使用Jwt，支持多终端认证系统。
支持加载动态权限菜单，多方式轻松权限控制。
我们的系统提供了一种安全的短信验证方式，为安卓应用提供了一个更加安全的登录方式。当用户进行安卓应用登录时，我们的服务端会使用用户的手机号生成一个 token，并将其传递给安卓应用。安卓应用会将该 token 存储在本地，并在后续的网络请求中将其作为请求头的一部分发送给服务端。服务端会使用 Spring Security 和 JwtFilter 对该请求进行拦截，如果请求是针对短信验证接口的，则可以直接放行。如果请求是针对其他接口的，则需要验证其请求头是否携带正确的 token。通过这种方式，我们可以保证用户登录的安全性，并防止未经授权的访问。
2 系统需求分析
2.1系统功能需求分析
在本次项目中，主要实现了文字图片的展示功能，包括登录、动态、美食、音乐剧、趋势和我的等模块。在这里，我们需要对这些模块进行功能需求分析，以便更好地实现应用程序的设计和开发。
1.登录模块
该模块需要实现用户的登录功能，支持短信登录方式。用户需要输入手机号码和验证码，系统将验证用户信息的正确性。如果验证成功，则跳转至主界面；如果验证失败，则提示用户错误信息。
2.动态模块
该模块需要实现动态信息的展示功能，包括轮播图、九宫格菜单和资讯界面。轮播图需要支持图片的展示和点击事件，点击后弹出相应的提示信息。九宫格菜单需要支持点击事件，点击后跳转至相应的功能界面。资讯界面需要支持图片和文字的展示，并支持点击事件，点击后跳转至详情页面。
3.美食模块
该模块需要实现美食信息的展示功能，需要使用瀑布流布局和CardView控件来实现图片和文字的展示。用户可以点击每个卡片，跳转至对应的详情页面。
4.音乐剧模块
该模块需要实现音乐剧信息的展示功能，同样需要使用瀑布流布局和CardView控件来实现图片和文字的展示。用户可以点击每个卡片，跳转至对应的详情页面。
5.趋势模块
该模块需要使用WebView控件来展示微博热搜界面，用户可以浏览热门话题和相关信息。
6.我的模块
该模块需要实现用户信息的展示和管理功能，包括用户头像、昵称、性别、生日等信息的展示。用户可以通过该模块进行设置、关于等菜单，执行退出登录操作，查看隐私条款等操作。

2.2系统界面需求分析
目前国内软件开发者在设计过程中很注重软件的开发技术及其具有的业务功能，而忽略勒用户对软件界面的需求，影响软件的易用性、友好性。界面设计要简约、美观，不能太复杂，让用户操作起来太繁琐，影响用户的使用体验，要在交互性与应用性上让用户感到舒适，让用户准确、高效、轻松、愉快地完成天气的查询，所以软件的友好性、易用性对软件系统至关重要。
2.3系统性能需求分析
软件在完成功能需求之后，还要进一步的优化，不能占用手机太多的内存资源，在交互性上使用户获得最佳感受，让使用不同手机设备的用户都能有良好地体验。


3 系统设计
3.1界面布局
在页面的布局上，因为界面设计的比较简单，所以只使用了瀑布流布局，相对布局和线性布局，瀑布流布局，相对布局与线性布局交替使用
            
   图 3-1 界面布局                     图 3-2 小贴士
 



   
           图 3-3 登录                        图 3-4 动态			


      
         图 3-5 美食			                 图 3-6 音乐剧	
     
         图 3-6  趋势                        图 3-7 我的


3.2  管理系统界面


4 系统实现
4.1 XHttp2框架实现网络请求
1、Android Studio导入方法，添加Gradle依赖
1.先在项目根目录的 build.gradle 的 repositories 添加:
allprojects {
     repositories {
        ...
        maven { url "https://jitpack.io" }
    }
}
2.然后在应用项目(一般是app)的 build.gradle 的 dependencies 添加:
以下是版本说明，选择一个即可。
androidx版本：2.0.0及以上
dependencies {
  ...
  implementation 'com.github.xuexiangjys:XHttp2:2.0.4'

  implementation 'com.google.code.gson:gson:2.8.5'
  implementation 'com.squareup.okhttp3:okhttp:3.10.0'
  implementation 'io.reactivex.rxjava2:rxjava:2.2.0'
  implementation 'io.reactivex.rxjava2:rxandroid:2.1.0'
}
support版本：1.0.4及以下
dependencies {
  ...
  implementation 'com.github.xuexiangjys:XHttp2:1.0.4'

  implementation 'com.google.code.gson:gson:2.8.2'
  implementation 'com.squareup.okhttp3:okhttp:3.10.0'
  implementation 'io.reactivex.rxjava2:rxjava:2.1.12'
  implementation 'io.reactivex.rxjava2:rxandroid:2.0.2'
}
3.在Application中初始化XHttpSDK
XHttpSDK.init(this);   //初始化网络请求框架，必须首先执行
XHttpSDK.debug("XHttp");  //需要调试的时候执行
XHttpSDK.setBaseUrl(SettingSPUtils.getInstance().getApiURL());  //设置网络请求的基础地址

                      图 4-1 Get请求


                         图 4-2 Post请求
XHttp2的优点：
1.XHttp2是一个功能非常强悍的网络请求库，使用RxJava2 + Retrofit2 + OKHttp组合进行封装。
2.支持多种Http请求方式，提供大量丰富的网络请求拦截器，支持三种方式进行网络请求，支持自定义网络请求协议，提供统一的异常处理机制，支持多种缓存策略，支持自定义请求响应实体，提供丰富的API方法，极大便捷了网络开发的效率。
4.2获取数据
获取数据主要使用的是XHttp2，以GET/POST方式向服务器发送请求，接收服务器响应。需注意：后端接口使用Spring Security, Json Web Token做了防护，只有短信验证接口是开放的，请求其他接口需在请求头携带token。


4.3解析数据
XHttp2框架可以自动解析数据，前提是有合适的实体类。
1.建立实体类

2.将集合传入回调函数，自动解析数据


3.设置数据，通知适配器数据集已更改（如果网络请求在另一个类里，可以加一个同步方法，检查网络请求是否完成，如果完成了，就返回响应数据，这样就不会得到一个空值）


4.4 XPage实现fragment

1.以FoodFragment 为例，这段代码创建了一个名为 FoodFragment 的页面类，用于展示美食列表。首先，我们使用 @Page 注解标识该类为页面类，并指定了页面名称、路由地址等信息。接着，在类中定义了一些成员变量和方法，用于处理页面逻辑。其中，initArgs() 方法用于自动注入参数，viewBindingInflate() 方法用于绑定页面布局，并返回一个 FragmentFoodBinding 对象，initViews() 方法用于初始化页面视图控件和数据，并使用 XHttpSDK 发起网络请求，获取美食列表数据，并将数据绑定到 RecyclerView 上，initListeners() 方法用于初始化监听器，实现下拉刷新和上拉加载功能。


4.4 XHttp2,Json Web Token,Spring Security实现短信验证码登录功能
1.安卓端发送请求，并在短信验证成功后存入token



2.后端JwtTokenFilter 拦截请求，并放行
@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {
        String requestUri = request.getRequestURI();
        if (requestUri != null && (requestUri.startsWith("/androidapi/send") || requestUri.startsWith("/androidapi/verify"))) {
            filterChain.doFilter(request, response); // 如果请求的URI以/send或/verify开头，则直接传递请求
            return;
        }
        if (requestUri != null && requestUri.startsWith("/androidapi/")) {
            String header = request.getHeader("Authorization");
            if (header != null && header.startsWith("Bearer ")) {
                String token = header.substring(7);
                if (jwtUtils.validateToken(token)) {
                    // 令牌验证成功，将请求传递给下一个过滤器或处理程序
                    filterChain.doFilter(request, response);
                    return;
                }
            }
            // 令牌验证失败，返回HTTP 401 Unauthorized响应
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        } else {
            // 如果请求URL不是/androidapi/，则直接传递请求
            filterChain.doFilter(request, response);
        }
    }
}
3.这段代码是一个短信验证码控制器，用于处理前端发送短信验证码和验证短信验证码的请求。首先使用 @RestController 和 @RequestMapping 注解指定了请求的路径前缀和请求方式。使用 @Autowired 注解将 SmsService、IUsersService、RedisTemplate、JwtUtils 等服务注入到控制器中，以便后续处理请求时使用。
在 send() 方法中，我们使用 PathVariable 注解来获取前端传递的手机号码，并生成一个 4 位数字的验证码。使用 SmsService 的 send() 方法将验证码通过短信发送给用户，并将验证码存入 Redis 中，有效期为 5 分钟。最后返回一个 AjaxResult 对象，表示短信发送成功。
在 verifyCode() 方法中，我们使用 RequestParam 注解来获取前端传递的手机号码和验证码，并从 Redis 中获取与手机号码对应的验证码。如果验证码正确，则使用 IUsersService 获取该用户的信息，如果用户存在，则更新用户的最后登录时间，如果用户不存在，则创建一个新用户，并设置最后登录时间。最后，使用 JwtUtils 生成一个 JWT 令牌，并将其返回给前端，表示验证码验证成功。如果验证码不正确，则返回一个 AjaxResult 对象，表示验证码错误。
总之，这段代码实现了短信验证码的发送和验证功能，可以用于用户注册、登录等场景。
@RestController
@RequestMapping("/androidapi")
@CrossOrigin
public class ApiSmsController {
    @Autowired
    private SmsService smsService;
    @Autowired
    private IUsersService usersService;
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private JwtUtils jwtUtils;
    @GetMapping("/send/{mobile}")
    public AjaxResult send(@PathVariable String mobile){
        // 实际上还要校验一下手机号是否合法，当然这步前端也可以校验
        Map<String,Object> map=new HashMap<>();
//        String fourBitRandom = RandomUtils.getFourBitRandom();// 工具类生成4位随机数
        String fourDigitCode = smsService.generateFourDigitCode();
        map.put("code", fourDigitCode);
        smsService.send(mobile,map);
        //将验证码存入redis
        redisTemplate.opsForValue().set("test:sms:code"+mobile, fourDigitCode, 5, TimeUnit.MINUTES);

        return AjaxResult.success("短信发送成功");
    }

    @PostMapping("/verify")
    public AjaxResult verifyCode(@RequestParam String mobile, @RequestParam String code) {
        String key = "test:sms:code" + mobile;
        String savedCode = (String) redisTemplate.opsForValue().get(key);
        if (savedCode != null && savedCode.equals(code)) {
            redisTemplate.delete(key);
            Users user = usersService.getUserByPhone(mobile);
            if (user != null) {
                user.setLastLoginTime(DateUtils.getNowDate());
                usersService.updateUsers(user);
            } else {
                user = new Users();
                user.setPhone(mobile);
                user.setLastLoginTime(DateUtils.getNowDate());
                usersService.insertUsers(user);
            }
            String token = jwtUtils.generateToken(user.getPhone());
            return AjaxResult.success("验证码正确",token);
        } else {
            return AjaxResult.error("验证码错误");
        }
    }

}
4.5 Json Web Token,Spring Security实现对后端接口的防护

1.JwtTokenFilter 拦截安卓端发出的请求，对登录接口的请求直接放行，对其他接口的请求必须有token
@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {
        String requestUri = request.getRequestURI();
        if (requestUri != null && (requestUri.startsWith("/androidapi/send") || requestUri.startsWith("/androidapi/verify"))) {
            filterChain.doFilter(request, response); // 如果请求的URI以/send或/verify开头，则直接传递请求
            return;
        }
        if (requestUri != null && requestUri.startsWith("/androidapi/")) {
            String header = request.getHeader("Authorization");
            if (header != null && header.startsWith("Bearer ")) {
                String token = header.substring(7);
                if (jwtUtils.validateToken(token)) {
                    // 令牌验证成功，将请求传递给下一个过滤器或处理程序
                    filterChain.doFilter(request, response);
                    return;
                }
            }
            // 令牌验证失败，返回HTTP 401 Unauthorized响应
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        } else {
            // 如果请求URL不是/androidapi/，则直接传递请求
            filterChain.doFilter(request, response);
        }
    }
2.token是由用户的手机号生成的，使用了jwtUtils
String token = jwtUtils.generateToken(user.getPhone());
return AjaxResult.success("验证码正确",token);

5 总结
通过这次实验掌握了Android应用程序的基本设计方法和思路，掌握了使用多种组件进行Android程序开发的方法。能够根据实际项目的需求，准确的分析出Android应用程序开发所可能涉及到的知识点，通过分析软件的需求，设计出用户界面和模块结构，并最终完成应用程序的开发和调试。这门课很有趣，带给了我许多兴趣与挑战，我相信在以后的学习中，会逐渐掌握这门课的技术。
