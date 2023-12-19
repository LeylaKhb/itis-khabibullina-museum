<html lang="en">
<#include "base.ftl">

<#macro title>Booking tickets</#macro>

<head>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>

    <script>
        $("#select-hall, #select-program, #date").change(function() {
            $.get("/booking?hall=" + $("#select-hall").val() + "&program=" + $("#select-program").val() + "&date=" + $("#date").val(), function (response) {
                $("#warning").text(response);
            })
        });
    </script>

</head>

<#macro content>
    <div>
<#--        <div>-->
<#--            <h3>List of events</h3>-->
<#--            <#if exhibitions?has_content>-->
<#--                <#list exhibitions as e>-->
<#--                    <div>-->
<#--                        Start date: ${e.startDate}-->
<#--                        <br>-->
<#--                        End date: ${e.endDate}-->
<#--                        <br>-->
<#--                        Name of exhibition: ${e.name}-->
<#--                        <br>-->
<#--                        Exhibition hall: ${e.hall}-->
<#--                        <br>-->
<#--                    </div>-->
<#--                </#list>-->
<#--            </#if>-->
<#--        </div>-->
<#--        <div>-->
<#--            <form action="book" method="post">-->
<#--                <div>-->
<#--                    <div>-->
<#--                        <h4>Booking tickets</h4>-->
<#--                    </div>-->
<#--                    <div>-->
<#--                        <div>-->
<#--                            Pick an exhibition hall-->
<#--                            <select name="hall" id="select-hall">-->
<#--                                <option value="1" selected>1 hall</option>-->
<#--                                <option value="2">2 hall</option>-->
<#--                                <option value="3">3 hall</option>-->
<#--                            </select>-->
<#--                        </div>-->

<#--                        <div>-->
<#--                            Choose your program-->
<#--                            <select name="program" id="select-program">-->
<#--                                <option value="small">With small group with guide</option>-->
<#--                                <option value="big">With big group with guide</option>-->
<#--                                <option value="individual">Individual without guide</option>-->
<#--                            </select>-->
<#--                        </div>-->

<#--                        <div>-->
<#--                            Choose date-->
<#--                            <input type="date" name="date" id="date" value="1970-01-01" >-->
<#--                        </div>-->

<#--                        <p style="color: red" id="warning"></p>-->

<#--                    </div>-->
<#--                    <div>-->
<#--                        <button type="submit">Book ticket</button>-->
<#--                    </div>-->
<#--                </div>-->
<#--            </form>-->
<#--        </div>-->
    </div>
</#macro>

</html>