function deleteMail(id) {
    $.ajax({
        type: "DELETE",
        url: "/mail/delete/" + id,
        contentType: "application/json",
        dataType: "json",
        success: function (resp) {
            switch (resp.code) {
                case 0:
                    layer.msg(resp.message, {icon: 6});
                    break;
                case 1:
                    layer.msg("更新失败", {icon: 5});
                    break;
                default:
            }
        },
        error: function () {
            layui.layer.alert("failed " + resp);
        }
    });
}