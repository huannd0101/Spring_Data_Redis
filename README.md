# Setup

1. Download and install Redis server:
<a href="/microsoftarchive/redis/releases/download/win-3.0.504/Redis-x64-3.0.504.msi" rel="nofollow">
    <span class="px-1 text-bold">Redis-x64-3.0.504.msi</span>
</a>    
2. Add dependence <br>
<pre>&lt;<span class="pl-ent">dependency</span>&gt;
  &lt;<span class="pl-ent">groupId</span>&gt;redis.clients&lt;/<span class="pl-ent">groupId</span>&gt;
  &lt;<span class="pl-ent">artifactId</span>&gt;jedis&lt;/<span class="pl-ent">artifactId</span>&gt;
  &lt;<span class="pl-ent">version</span>&gt;3.7.0-RC1&lt;/<span class="pl-ent">version</span>&gt;
&lt;/<span class="pl-ent">dependency</span>&gt;
</pre>
<pre>&lt;<span class="pl-ent">dependency</span>&gt;
  &lt;<span class="pl-ent">groupId</span>&gt;org.springframework.data&lt;/<span class="pl-ent">groupId</span>&gt;
  &lt;<span class="pl-ent">artifactId</span>&gt;spring-data-redis&lt;/<span class="pl-ent">artifactId</span>&gt;
  &lt;<span class="pl-ent">version</span>&gt;2.5.6&lt;/<span class="pl-ent">version</span>&gt;
&lt;/<span class="pl-ent">dependency</span>&gt;
</pre>

3. Config trong file application.properties

spring.cache.type=redis <br>
spring.redis.host=localhost <br>
spring.redis.port=6379 <br>

Or config in RedisConfig file

4. Annotation

- @Cacheable(value = "user", key = "#id"): cache bằng id của user, dùng cho phương thức GET
- @CachePut(value = "user", key = "#userDTO.username"): Update dữ liệu trong cache khi có sự thay đổi.
- @CacheEvict(value = "user", key = "#id"):  xóa dữ liệu trong cach khi xóa dữ liệu

5. Lưu ý phải kích hoạt @EnableCaching