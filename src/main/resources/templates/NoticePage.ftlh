<#-- @ftlvariable name="createForm" type="ru.itmo.wp.form.NoticeCredentials" -->
<#-- @ftlvariable name="message" type="java.lang.String" -->
<#-- @ftlvariable name="error" type="java.lang.String" -->

<#import "commons.ftlh" as c>

<@c.page>
    <div class="article form-box">
        <div class="header">Post notice</div>
        <div class="body">
            <form method="post" action="">
                <input type="hidden" name="action" value="create"/>
                <div class="field">
                    <div class="name">
                        <label for="content">Content</label>
                    </div>
                    <div class="value">
                        <textarea id="content" name="content" rows="5" cols="24" maxlength="65535"></textarea>
                    </div>
                    <@c.error "createForm.content"/>
                </div>
                <div class="button-field">
                    <input type="submit" value="Add">
                </div>
            </form>
        </div>
    </div>
</@c.page>

