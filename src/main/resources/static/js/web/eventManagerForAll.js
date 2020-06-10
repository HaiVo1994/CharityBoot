class EventManagerForAll {
    getList(page,size, recentPage){
        var proposalList = $("#listEvent"),
            url = "";
        switch (recentPage) {
            case 0:
                url = "eventRest/" + page + "/" + size;
                break;
            case 1:
                url = "event_manager/eventAcceptRegister/" + page + "/" + size;
                break;
            case 2:
                url = "event_manager/eventMustFinish/" + page + "/" + size;
                break;
        }
        getJson(url).done(
            function (data) {
                var html = "";
                proposalList.html(html);
                $.each(data,
                    function (index, event) {
                        var linkCause = "/event/single_event/" + event.id,
                            imageSrc = "",
                            contentOfVote = event.vote.summary != null? event.vote.summary: "Chưa Có",
                            listImage = event.vote.voteImages,
                            imgIndex = 0;
                        if (listImage.length>0){
                            imgIndex = Math.floor(Math.random() * listImage.length);
                            imageSrc = listImage[imgIndex].source;
                        }
                        else {
                            imageSrc = "/img/post-1.jpg";
                        }
                        var beginDate = new Date(event.beginDate),
                            finishDate = new Date(event.finishDate),
                            registration= new Date(event.registrationDeadline);
                        html =
                            "<div class='col-md-4 main-card'>" +
                            "<div class='causes'>" +
                            "<div class='causes-img'>" +
                            "<a href='" + linkCause + "'>" +
                            "<img src='" + imageSrc+ "' alt=''>" +
                            "</a>" +
                            "</div>" +
                            "<div class='causes-content'>" +
                            "<h3><a href='" + linkCause + "'>" + event.vote.tittle + "</a></h3>" +
                            "<p>Ngày Bắt Đầu: " + getStringDate(beginDate) + "</p>" +
                            "<p>Ngày Kết Thúc: " + getStringDate(finishDate) + "</p>" +
                            "<p>Hạn Đăng Ký: " + getStringDate(registration) + "</p>" +
                            "<p class='summaryOfVote'>" + contentOfVote + "</p>" +

                            "<a href='" + linkCause + "' class='primary-button causes-donate'>Chi Tiết</a>" +
                            "</div>" +
                            "</div>" +
                            "</div>";
                        proposalList.append(html);
                    }
                );
            }
        );
    }
    setPage(page,size, recentPage){
        var previousPage = page - 1,
            pagination = $("#main").find(".pagination"),
            url = "";
        switch (recentPage) {
            case 0:
                url = "eventRest/" + size;
                break;
            case 1:
                url = "event_manager/countEventAcceptRegister/" + size;
                break;
            case 2:
                url = "event_manager/countEventMustFinish/" + size;
                break;

        }
        if (previousPage >= 0){
            pagination.html("<li>" +
                "<a class='page-link' tabindex='-1' onclick='transferPage(" + previousPage + ");'>Previous</a>" +
                "</li>");
        }
        else {
            pagination.html("<li class='disabled'>" +
                "<a class='page-link' onclick='transferPage(" + page + ");' tabindex='-1'>Previous</a>" +
                "</li>");
        }

        getJson("eventRest/" + size).done(
            function (data) {
                var totalPage = data;
                var show = 0;
                for (var i=0; i<totalPage;i++){
                    show = i + 1;
                    if (i === page)
                        pagination.append("<li class='active'>" +
                            "<a onclick='transferPage(" + i + ");' tabindex='-1'>" + show + "</a>" +
                            "</li>");
                    else
                        pagination.append("<li value=''>" +
                            "<a onclick='transferPage(" + i + ");' tabindex='-1'>" + show + "</a>" +
                            "</li>");
                }
                var nextPage = page + 1;
                if (nextPage<totalPage){
                    pagination.append("<li>" +
                        "<a onclick='transferPage(" + nextPage + ");' tabindex='-1'>Next</a>" +
                        "</li>");
                }
                else{
                    pagination.append("<li class='disabled'>" +
                        "<a onclick='transferPage(" + page + ");' tabindex='-1'>Next</a>" +
                        "</li>");
                }

            }
        );
    }

    getSingle(){
        var votePage = this;
        getJson("eventRest/get_single/"+id).done(
            function (data) {
                var main = $("#main"),
                    listImage = data.vote.voteImages,
                    imgIndex = 0,
                    imgLocation = main.find(".article-img").find("img");
                if (listImage.length>0){
                    imgIndex = Math.floor(Math.random() * listImage.length);
                    imgLocation.attr("src", listImage[imgIndex].source);
                }
                else {
                    imgLocation.attr("src", "/img/post-1.jpg");
                }
                main.find(".article-content").find(".article-title").html(data.vote.tittle);
                var dateBegin = new Date(data.beginDate),
                    dateFinish = new Date(data.finishDate),
                    deadline = new Date(data.registrationDeadline);
                main.find(".article-content").find(".article-meta")
                    .append("<li>Ngày Bắt Đầu: " + getStringDate(dateBegin) + "</li>");
                main.find(".article-content").find(".article-meta")
                    .append("<li>Ngày Kết Thúc: " + getStringDate(dateFinish) + "</li>");
                // main.find(".article-content").find(".article-meta")
                //     .append("<li>Ngày Bắt Đầu: " + getStringDate(dateBegin) + "</li>");
                main.find(".article-content").find(".article-meta")
                    .append("<li>Hạn Đăng Ký: " + getStringDate(deadline) + "</li>");
                main.find(".article-content").find(".article-meta")
                    .append("<li>Địa Điểm: " + data.location + "</li>");
                $("#contentOfVote").html("<p class='content'>" + data.vote.content + "</p>");
                $("#note").html(data.note);

                var voteId = data.vote.id;
                getJson('categoryRest/categoryOfVote/' + voteId).done(
                    function (category) {
                        $.each(category,
                            function (index, row) {
                                $("#main").find(".article-tags-share").find(".tags").append("<li><a href='/category/"+ row.id +"'>" + row.name + "</a></li>");
                            })
                    }
                );

                getJson('donateRest/donateAmount/' + voteId).done(
                    function (raised) {
                        if (typeof raised === "undefined")
                            raised = 0;
                        $("#main").find(".causes-raised").find("strong").html(raised + "$");
                    }
                );
                var currentDate = new Date(),
                    registerButton = $("#buttonRegister"),
                    checkRegisterButton = $("#buttonListRegister"),
                    finishButton = $("#buttonFinishEvent");

                registerButton.prop("disabled", false);
                registerButton.show();
                checkRegisterButton.prop("disabled", false);
                checkRegisterButton.show();
                finishButton.prop("disabled", true);
                finishButton.hide();
                if (currentDate>deadline){
                    registerButton.prop("disabled", true);
                    registerButton.hide();

                }
                if (currentDate > dateFinish){
                    finishButton.prop("disabled", false);
                    finishButton.show();
                }
                if (currentDate > dateBegin){
                    checkRegisterButton.prop("disabled", true);
                    checkRegisterButton.hide();
                }
            }
        );
    }
    register(id){
        var dataUrl = urlRoot + "eventRest/register/" + id,
            alertModal = $("#alertModal");
        $.ajax({
            url: dataUrl ,
            method: 'PUT',
            dataType: 'json',
            contentType: 'application/json'
        })
            .done(function (data) {
                    $("#register").modal("hide");
                    alertModal.find(".modal-body").html("<p class='success'>" + data.result + "</p>");
                    alertModal.modal("show");
                    checkUserRegister();
                }
            )
            .fail(
                function () {
                    $("#register").modal("hide");
                    alertModal.find(".modal-body").html("<p class='alert'>Hết Hạn Đăng Ký Hoạt Bạn Đã Đăng Ký Rồi</p>");
                    alertModal.modal("show");
                }
            );
    }
}