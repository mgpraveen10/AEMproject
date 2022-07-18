use( function () {

    var linksList = [];
    var linkArray = properties.get("linkmultifield");
    if(linkArray != null){
        for(var i = 0; i < linkArray.length; i++) {
        var singleObj = {};
        var itemObject =  JSON.parse(linkArray[i]);
        singleObj['title'] = itemObject.title;
        singleObj['path'] = itemObject.titlelink;
        linksList.push(singleObj);
    };
    }

   return {
        linksList: linksList
    };
});
