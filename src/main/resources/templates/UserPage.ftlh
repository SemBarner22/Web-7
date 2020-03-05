<#-- @ftlvariable name="user" type="ru.itmo.wp.domain.User" -->
<#import "commons.ftlh" as c>

<@c.page>
    Welcome on Codeforces!
    <#if user?has_content>
        <div>${user.id}</div>
        <div>${user.login}</div>
        <div>${user.creationTime}</div>
    <#else>
        <div>No such User</div>
    </#if>
</@c.page>
