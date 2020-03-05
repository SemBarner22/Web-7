<#-- @ftlvariable name="user" type="ru.itmo.wp.domain.User" -->
<#-- @ftlvariable name="users" type="ru.itmo.wp.domain.User[]" -->
<#import "commons.ftlh" as c>

<@c.page>
    <div class="users datatable">
        <div class="caption">User</div>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>Login</th>
                <th>Created</th>
                <th>Disability</th>
            </tr>
            </thead>
            <tbody>
            <#if users?size==0>
                <tr class="noData">
                    <td colspan="3">
                        No data
                    </td>
                </tr>
            <#else>
                <#list users as u>
                    <tr>
                        <td class="id">${u.id}</td>
                        <td class="login">${u.login}</td>
                        <td class="creationTime">${u.creationTime}</td>
                        <td>
                            <#if user?has_content && u!=user>
                                <form method="post" action="">
                                    <input type="hidden" name="action" value="disability"/>
                                    <input type="hidden" name="id" value="${u.id}">
                                    <input type="hidden" name="disability" value="${(u.disabled)?c}">
                                    <input name="article" id="button" type="submit" value=
                                    "<#if u.disabled>Disable<#else>Enable</#if>">
                                </form>
                            </#if>
                        </td>
                    </tr>
                </#list>
            </#if>
            </tbody>
        </table>
    </div>
</@c.page>
