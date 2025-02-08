# day01

搭建前端框架

- 下载vscode

- 下载npm

- 配置npm的环境

- vs打开文件夹的方式打开项目

- 点击package.json文件，可以运行

- 或者点击目录，然后使用命令

  ```
  npm install //加载前端所需要的库
  npm run dev //运行开发环境
  ```

- 或者使用webstorm打开
- 文件结构构成
  <img src="C:\Users\86157\AppData\Roaming\Typora\typora-user-images\image-20250119182036680.png" alt="image-20250119182036680" style="zoom:80%;" />

src主要存放的源代码，最主要的，其他都是配置文件

- api主要用来像后端·发送请求使用
- compentents是组件，页面的一些组件
- router，代表路由，点击某些按钮，跳到哪些页面
- utils通用工具类，
- views，视图，存储的一些界面



## Swagger



简介：动态生成项目接口文档的一个东西，并且可以在这里发送请求，进行测试

配置：

```
springfox-swagger2
springfox-swaggerui 
2.9.2版本
```

SwaggerConfig

```java
@Configuration // 标明是配置类
@EnableSwagger2 //开启swagger功能
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)  // DocumentationType.SWAGGER_2 固定的，代表swagger2
                //.groupName("分布式任务系统") // 如果配置多个文档的时候，那么需要配置groupName来分组标识
                .apiInfo(apiInfo()) // 用于生成API信息
                .select() // select()函数返回一个ApiSelectorBuilder实例,用来控制接口被swagger做成文档
                // 扫描指定包下的接口，最为常用
                .apis(RequestHandlerSelectors.basePackage("com.maolong.controller"))
                //.withClassAnnotation(RestController.class) // 扫描带有指定注解的类下所有接口
                //.withMethodAnnotation(PostMapping.class) // 扫描带有指定注解的方法接口
                .apis(RequestHandlerSelectors.any()) // 扫描所有

                // 选择所有的API,如果你想只为部分API生成文档，可以配置这里
                .paths(PathSelectors.any()
                        //.any() // 满足条件的路径，该断言总为true
                        //.none() // 不满足条件的路径，该断言总为false（可用于生成环境屏蔽 swagger）
                        //.ant("/user/**") // 满足字符串表达式路径
                        //.regex("") // 符合正则的路径
                )
                .build();
    }

    /**
     * 用于定义API主界面的信息，比如可以声明所有的API的总标题、描述、版本
     * @return
     */
    private ApiInfo apiInfo() {

        Contact contact = new Contact(
                "我是高茂龙", // 作者姓名
                "https://gitee.com/gao-maolong/course-design", // 作者网址
                "1121790684@qq.com"); // 作者邮箱

        return new ApiInfoBuilder()
                .title("SpringBoot项目API") //  可以用来自定义API的主标题
                .description("SpringBoot项目SwaggerAPI管理") // 可以用来描述整体的API
                .termsOfServiceUrl("https://www.baidu.com") // 用于定义服务的域名（跳转链接）
                .version("1.0") // 可以用来定义版本
                .license("Swagger-的使用教程")
                .licenseUrl("https://blog.csdn.net")
                .contact(contact)
                .build(); //
    }
}

```

注意：需要配置application.yml文件,否则会出现问题

- fail，项目启动失败，Failed to start bean 'documentationPluginsBootstrapper';
- 加入EnableWebMvc后，能启动了，但是是不对的，此时访问静态默认页面http://localhost:8080/swagger-ui.html界面，映射失败。此时加入下面的yml配置，就可以完成以上的问题

```yaml
spring:
  mvc:
    pathmatch:
      matching-strategy: ant_path_matcher

```

#### 常用注解

![image-20250119192338221](C:\Users\86157\AppData\Roaming\Typora\typora-user-images\image-20250119192338221.png)

- ![image-20250119192404580](C:\Users\86157\AppData\Roaming\Typora\typora-user-images\image-20250119193746358.png)
- ![image-20250119192422506](C:\Users\86157\AppData\Roaming\Typora\typora-user-images\image-20250119192422506.png)
- ![image-20250119192448544](C:\Users\86157\AppData\Roaming\Typora\typora-user-images\image-20250119192448544.png)
- ![image-20250119193831773](C:\Users\86157\AppData\Roaming\Typora\typora-user-images\image-20250119193831773.png)![image-20250119193910170](C:\Users\86157\AppData\Roaming\Typora\typora-user-images\image-20250119193910170.png)
- ![image-20250119192508644](C:\Users\86157\AppData\Roaming\Typora\typora-user-images\image-20250119192508644.png)
- ![image-20250119192542988](C:\Users\86157\AppData\Roaming\Typora\typora-user-images\image-20250119192542988.png)![image-20250119194010388](C:\Users\86157\AppData\Roaming\Typora\typora-user-images\image-20250119194010388.png)
- ![image-20250119192600281](C:\Users\86157\AppData\Roaming\Typora\typora-user-images\image-20250119192600281.png)![image-20250119194116224](C:\Users\86157\AppData\Roaming\Typora\typora-user-images\image-20250119194116224.png)
- ![image-20250119192613860](C:\Users\86157\AppData\Roaming\Typora\typora-user-images\image-20250119192613860.png)![image-20250119194203327](C:\Users\86157\AppData\Roaming\Typora\typora-user-images\image-20250119194203327.png)
- ![image-20250119192622576](C:\Users\86157\AppData\Roaming\Typora\typora-user-images\image-20250119192622576.png)

## 代码生成器

pom依赖

```
swagger2
mybatis-plus-generator

```



```
public class MyGenerate {
    public static void main(String[] args) {
        String route="C:\\Users\\86157\\Desktop\\MyFiles\\Java\\Java-Project\\module-1";

        FastAutoGenerator.create("jdbc:mysql://localhost:3306/java_project", "root", "gml20031117")
                .globalConfig(builder -> {
                    builder.author("maolong") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir(route+"\\src\\main\\java\\"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com") // 设置父包名
                            .moduleName("maolong") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, route+"\\src\\main\\resources\\com\\maolong")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("user"); // 设置需要生成的表名
//                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();

    }
}
```

**问题**：在搭建maven聚合继承项目时，没改父项目的打包方式，应该为pom方式



# day02

- 前后端如何联合

  - 前端启动服务器后，后端也启动服务器，然后前端请求后端的服务路径，就可以操作了

  - 问题：出现了跨域问题：书写配置类解决。（前端的端口号为localhost：9999，后端为localhost：8080），书写配置类后，从别的服务器访问其他服务器的接口就可以访问了

  - ```
    
    @Configuration
    public class WebConfig implements WebMvcConfigurer {
        @Bean
        public CorsFilter corsFilter() {
            // 创建一个 CorsConfiguration 对象，用于定义 CORS 配置
            CorsConfiguration config = new CorsConfiguration();
            // 设置允许的请求源，允许所有来源。`*` 表示任何域名都可以访问资源
            config.setAllowedOriginPatterns(Collections.singletonList("*"));
            // 设置允许的 HTTP 方法。`*` 表示允许所有 HTTP 方法（如 GET, POST, PUT, DELETE 等）
            config.addAllowedMethod("*");
            // 设置允许的请求头。`*` 表示允许所有请求头
            config.addAllowedHeader("*");
            // 设置是否允许带有凭据（如 cookies）的请求。true 表示允许
            config.setAllowCredentials(true);
            // 创建一个 UrlBasedCorsConfigurationSource，设置跨域配置源
            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            // 注册 CORS 配置到指定的 URL 路径上，"/**" 表示对所有请求路径都启用这个 CORS 配置
            source.registerCorsConfiguration("/**", config);
            // 返回一个 CorsFilter 实例，Spring 会自动使用这个过滤器来处理跨域请求
            return new CorsFilter(source);
        }
    }
    ```

- 问题2：出现了一个问题，，就是yml没配置新的数据库导致，注意

- 问题3：npm版本过新，导致没法使用，换为了node16

- 问题4：不熟悉前端架构

  - 下载vscode，配置插件

  - 下载node，配置环境

  - vscode打开项目，找到package.json，这个很重要，里面包好了需要的依赖，以及运行环境

  - 可以直接点击package.json中的开始，也可以再终端输入命令，注意输入命令时，点击的是项目

  - 对于前端环境的熟悉

    ```
    ├── /build/          # 项目构建(webpack)相关配置
    ├── /config/         # 项目开发环境配置   //配置在哪个端口启动的，服务器地址等等，。。。。
    ├── /src/            # 源码目录   ！这个最重要的，主要看这个
    │ ├── /api/          # 请求   ！把一些异步请求封装在这里，整的规范一点罢了
    │ ├── /assets/       # 组件静态资源(图片)
    │ ├── /components/   # 公共组件   ！类似于所有页面都需要的东西
    | ├── /api/          # 请求接口   ！重复了上面的api
    │ ├── /router/       # 路由配置   !这个就相当于window.href=,,进行页面跳转的
    │ ├── /vuex/         # vuex状态管理  ！额，这个没太看，
    │ ├── /views/        # 路由组件(页面维度)  ！这个是一些大页面，component是页面上的一点小东西
    │ ├── /config/       # 接口配置文件（请求地址）  
    │ ├── App.vue        # 组件入口   
    │ └── main.js        # 程序入口    //这个是头头，引用了其他的所有东西把，基本
    ├── /static/         # 非组件静态资源     //静态资源，好像没用到
    ├── .babelrc         # ES6语法编译配置
    ├── .editorconfig    # 定义代码格式
    ├── .eslintignore    # ES6规范忽略文件
    ├── .eslintrc.js     # ES6语法规范配置
    ├── .gitignore       # git忽略文件
    ├── index.html       # 页面入口
    ├── package.json     # 项目依赖       ！项目依赖很重要
    └── README.md        # 项目文档
    ```

    

- 解决5：配置了object和json的转换，导入了jsckon-core的依赖

  ```java
  import com.fasterxml.jackson.databind.ObjectMapper;
  public class JsonObjUtil {
      private static final ObjectMapper objectMapper = new ObjectMapper();
      public static String toJson(Object obj) throws Exception {
              return objectMapper.writeValueAsString(obj);
      public static <T> T toObject(String json, Class<T> clazz) throws Exception {
              return objectMapper.readValue(json, clazz);
  ```

  





- 2025/2/1完成情况

- 完成了用户界面和部门管理界面

- 问题

  - 保存和更新方法，使用一个接口，通过先判断id是否存在来判定保存还是更新

  - 拦截器MDC获取数据的时候，获取不到，原来是忘了拦截部门接口的api

  - 对于json数据，可以使用map来接收，然后只获取其中的一个字段的信息

  - 对于分页插件PageHelper的使用

  - ```java
    PageHelper.startPage(page,limit)
    Page<User> pages=usermapper.getByConditions();
    //参数
    return PageResult(<list<T> records>,<total>)
    ```

    

- 文档被拦截了，导致knife4j不能使用，解决

- ```
          excludePath.add("/doc.html");            // Knife4j 页面
          excludePath.add("/webjars/**");          // 静态资源
          excludePath.add("/swagger-resources/**");// Swagger 配置
          excludePath.add("/v2/api-docs");         // API 文档 JSON
          excludePath.add("/v3/api-docs");
          excludePath.add("/swagger-ui/**");       // Swagger UI 资源
          excludePath.add("/User/login");
  ```

  之前的原因是只放行了/doc.html,但是不够，由好多构成

  或者其他方法

  ```
  public class JwtTokenInterceptor extends HandlerInterceptorAdapter {
      @Override
      public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
          String requestPath = request.getRequestURI();
          if (requestPath.equals("/doc.html")) {
              return true; // 直接放行
          }
  ```

  

- @Api(tags=""),@ApiOperation("")是这两个注解，别用错啦





如何返回Tree树控件所需要的json数据呢

算法内容

```
public class TestTreeData {
    @Data
    @AllArgsConstructor
    class Node {
        int id;
        int parentId;
        String name;
        List<Node> children;
    }

    @Test
    public void test() {
        List<Node> list = new ArrayList<>();
        list.add(new Node(1, 1, "*", new ArrayList<>()));
        list.add(new Node(2, 1, "A", new ArrayList<>()));
        list.add(new Node(3, 1, "B", new ArrayList<>()));
        list.add(new Node(4, 2, "A1", new ArrayList<>()));
        list.add(new Node(5, 2, "A2", new ArrayList<>()));
        list.add(new Node(6, 3, "B1", new ArrayList<>()));
        list.add(new Node(7, 6, "B2", new ArrayList<>()));

        // 创建父ID到子节点列表的映射
        Map<Integer, List<Node>> parentIdToChildren = new HashMap<>();
        for (Node node : list) {
            parentIdToChildren.computeIfAbsent(node.getParentId(), k -> new ArrayList<>()).add(node);
        }

        List<Node> rootNodes = new ArrayList<>();

        for (Node node : list) {
            int nodeId = node.getId();
            int parentId = node.getParentId();

            // 判断是否为根节点（这里假设根节点的parentId等于自身id）
            if (nodeId == parentId) {
                rootNodes.add(node);
                // 获取子节点并排除自身（如果存在）
                List<Node> children = parentIdToChildren.getOrDefault(parentId, Collections.emptyList())
                        .stream()
                        .filter(child -> child.getId() != nodeId) // 排除自身作为子节点
                        .collect(Collectors.toList());
                node.getChildren().addAll(children);
            } else {
                // 非根节点直接设置子节点
                List<Node> children = parentIdToChildren.get(nodeId);
                if (children != null) {
                    node.getChildren().addAll(children);
                }
            }
        }

        // 输出根节点及其树结构
        System.out.println(rootNodes);
    }
}
```





## 数据库如何设置Boolean字段

数据库使用tinyint就行，然后java使用Boolean类型，便可以注入，1对应true，0 false



## 自定义序列化

问题：前端传过来的是 ids="[1,2,3]"，是一个字符串，想用list接收咋办

- 自定义utils

  ```
  /**
   * 将类似于 “[1,2,3]” 的字符串转换为 List<Integer>[1,2,3]
   */
  public class StringToListUtil {
      public static List<Integer> stringToList(String moduleIdsJson) {
          List<Integer> moduleIds = new ArrayList<>();
          if (moduleIdsJson != null && !moduleIdsJson.isEmpty()) {
              String[] ids = moduleIdsJson.replace("[", "").replace("]", "").split(",");
              for (String id : ids) {
                  moduleIds.add(Integer.parseInt(id.trim()));
              }
          }
          return moduleIds;
      }
  }
  ```

  

- 使用序列化转换器

```
/**
 * 自定义反序列化器，用于将JSON字符串转换为List<Integer>
 */

public class StringListDeserializer extends JsonDeserializer<List<Integer>> {

    @Override
    public List<Integer> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        // 1. 读取原始字符串（如 "[1,2,3]"）
        String rawValue = p.getValueAsString();

        // 2. 去除字符串两端的方括号和空格
        String cleaned = rawValue.replaceAll("[\\[\\]\\s]", "");

        // 3. 按逗号分割并转换为Integer列表
        return Arrays.stream(cleaned.split(","))
                .filter(s -> !s.isEmpty())
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
```

```
@Data
public class RoleRightDTO implements Serializable {
    private Integer roleId;
    @JsonDeserialize(using = StringListDeserializer.class)
    private List<Integer> moduleIds;
}
```

待完成：菜单那个界面不是树形结构，咋整？前端页面的问题，因为uid，压根就没有啊



### 手写下拉框组件

```vue
        <el-form-item label="部门" prop="deptName">
          <el-select v-model="editForm.deptName" placeholder="请选择" class="userRole">
            <el-option
              v-for="item in DeptOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>


data{
      DeptOptions:[],
}
methods:{
  getDept: function (){
      userDept()
        .then(res => {
          console.log(res)
          if (res.data.success) {
            console.log(res)
            this.DeptOptions=res.data.data
            this.$message({
              type: 'success',
              message: '部门查询成功'
            })
          } else {
            this.$message({
              type: 'info',
              message: res.msg
            })
          }
        })
        .catch(err => {
          this.editFormVisible = false
          this.loading = false
          this.$message.error('保存失败，请稍后再试！')
        })

}


export const userDept = (params) => { return axios.get("/api/User/dept" + "?token=" + localStorage.getItem('logintoken'))   };
```

### 冗余字段

![image-20250206150057226](C:\Users\86157\AppData\Roaming\Typora\typora-user-images\image-20250206150057226.png)

<img src="C:\Users\86157\AppData\Roaming\Typora\typora-user-images\image-20250206150110618.png" alt="image-20250206150110618" style="zoom:50%;" />

通过设计冗余字段来减少sql查询次数

## Redis缓存token



```java
@PostMapping("/login")
Result login(@RequstBody UserDTO userDTO){
    User user=mysql.(userDTO);  //数据库查询
    
    redis.set("token",user,ttl); //key->"token",value->"user对象",ttl->过期时间
    
    String jwt=JwtUtil.createJwt(); //生成jwt
    
    return Result.success(jwt) //返回前端jwt
}
```

```java
public class JwtTokenInterceptor{
	preHandle(){
      //先判断redis中token有无东西，有东西获取，赋值，放行  
      user = redis.get("token");
      if(user!=null){
          MDC.set()......
          return true;//放行
      }
      
      //解析token
      ....
    }
}
```



## mysql数据库内容区分

- 如果字符集是utf8mb4_bin，内容区分大小写，role和Role不一样
- 如果是utf8mb4_general_ci,内容不区分大小写，role和Role一样



### springboot默认没有整合aop注解，需要自己加

```xml
<!--        aop注解-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-aop</artifactId>
        </dependency>
@Aspect
@PointCut
@EnableAspectProxy
```



待完成：每次jwt到时间了，不知道，希望抛异常，重定向



### 问题：字段映射问题

- 在使用mybatis-plus时，java实体主键字段为permissionId，数据库为id,加入@TableId（“id"),映射不成功
- 解决：我配置的是mybatis-plus，但是我方法使用的是xml方式，后面改为起别名，或者resultMap方式完成







![image-20250206233955536](C:\Users\86157\AppData\Roaming\Typora\typora-user-images\image-20250206233955536.png)

解决

```
            <dependency>
                <groupId>com.github.pagehelper</groupId>
                <artifactId>pagehelper-spring-boot-starter</artifactId>
                <version>1.3.0</version>
            </dependency>
```



学习引入坐标方式

```xml
<properties>
        <mybatis.spring>2.2.0</mybatis.spring>
        <lombok>1.18.20</lombok>
        <fastjson>1.2.76</fastjson>
        <commons.lang>2.6</commons.lang>
        <druid>1.2.1</druid>
        <pagehelper>1.3.0</pagehelper>
        <aliyun.sdk.oss>3.10.2</aliyun.sdk.oss>
        <knife4j>3.0.2</knife4j>
        <aspectj>1.9.4</aspectj>
        <jjwt>0.9.1</jjwt>
        <jaxb-api>2.3.1</jaxb-api>
        <poi>3.16</poi>
    </properties>
    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.mybatis.spring.boot</groupId>
                <artifactId>mybatis-spring-boot-starter</artifactId>
                <version>${mybatis.spring}</version>
            </dependency>

            <dependency>
                <groupId>org.projectlombok</groupId>
                <artifactId>lombok</artifactId>
                <version>${lombok}</version>
            </dependency>
```

## maven包管理

- 创建空的springboot《java-project》工程，删除无用的，只留下一个pom就行，删除pom中无用的，并且把打包方式设置为pom

  - ```xml
        <modelVersion>4.0.0</modelVersion>
        <parent>
            <artifactId>spring-boot-starter-parent</artifactId>
            <groupId>org.springframework.boot</groupId>
            <version>2.7.3</version>
        </parent>
        
        <groupId>com.sky</groupId>
        <artifactId>sky-take-out</artifactId>
    //打包方式
        <packaging>pom</packaging>
        <version>1.0-SNAPSHOT</version>
        //子模块
        <modules>
            <module>sky-common</module>
            <module>sky-pojo</module>
            <module>sky-server</module>
        </modules>
    //版本管理
        <properties>
            <jaxb-api>2.3.1</jaxb-api>
            <poi>3.16</poi>
        </properties>
        <dependencyManagement>
            <dependencies>
                <dependency>
                    <groupId>com.github.wechatpay-apiv3</groupId>
                    <artifactId>wechatpay-apache-httpclient</artifactId>
                    <version>0.4.8</version>
                </dependency>
            </dependencies>
        </dependencyManagement>
    </project>
    
    ```

    

- 创建common，pojo,普通的maven模块即可，选中父项目为

  - ```xml
    <parent>
            <artifactId>sky-take-out</artifactId>
            <groupId>com.sky</groupId>
            <version>1.0-SNAPSHOT</version>
        </parent>
        <modelVersion>4.0.0</modelVersion>
        <artifactId>sky-common</artifactId>
        <dependencies>
            。。。
        </dependencies>
    </project>
    
    ```

    

- 创建子模块server，在子模块中使用到了前面的commen和pojo里面的东西，导入前面两个模块的坐标

  - ```
     <parent>
            <artifactId>sky-take-out</artifactId>
            <groupId>com.sky</groupId>
            <version>1.0-SNAPSHOT</version>
        </parent>
        <modelVersion>4.0.0</modelVersion>
        <artifactId>sky-server</artifactId>
        <dependencies>
        //前面两个子模块的坐标
            <dependency>
                <groupId>com.sky</groupId>
                <artifactId>sky-common</artifactId>
                <version>1.0-SNAPSHOT</version>
            </dependency>
            <dependency>
                <groupId>com.sky</groupId>
                <artifactId>sky-pojo</artifactId>
                <version>1.0-SNAPSHOT</version>
            </dependency>
        </dependencies>
    
        <build>
            <plugins>
                <plugin>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-maven-plugin</artifactId>
                </plugin>
            </plugins>
        </build>
    
    </project>
    
    ```

    



//bug

分页查询出了bug，12条都在一个页面上，配置原因，没加上limit等语句压根


