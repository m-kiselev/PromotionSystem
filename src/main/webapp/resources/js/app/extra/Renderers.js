/**
 * This file contains renderer and converter functions
 *
 * @author mkiselev
 */
function titleRenderer(value){
    if (value != null) return value.title;
}
function nameRenderer(value){
    if (value != null) return value.name;
}
function titlesRenderer(value){
    return fetchField(value,"title");
}
function shortTitleRenderer(value){
    var shortTitle = '';
    if (value != null && typeof value !== 'undefined') {
        shortTitle = value.shortTitle;
    }
    return shortTitle;
}
function shortTitlesRenderer(value){
    return fetchField(value,"shortTitle");
}
//function myLSRenderer(value,b,rec){
//    if (value != null) {
//        if (typeof rec.data.licenseAgreement != 'undefined') {
//            return "<a onclick='openLicenseAgreementByid("+rec.data.licenseAgreement.id+")'>"+value.number+"</a>";
//        } else {
//            return "<a onclick='openLicenseAgreementByid("+rec.data.parentDocumentId+")'>"+value.number+"</a>";
//        }
//    }
//}
//function psDocumentStatusRenderer(value){
//    if (value != null) {
//        if (value == 'STORED')    return "Записан";
//        if (value == 'ACCOUNTED') return "Проведен";
//    }
//}

//function jobMessageRenderer(value,metadata,record){
//    var a = '';
//    if  ( (typeof record !== 'undefined') && (record != null) 
//        && (record.data.startedAt != null) &&  (record.data.finishedAt != null)){
//        if (record.data.success){
//            metadata.tdAttr = 'data-qtip="' + record.data.status + '"';
//            a = record.data.status; 
//        } else if (record.data.discriminatorValue=='xls-import'){
//            a='<a href="admin/xlserror/'+record.data.id+'">Получить файл с ошибками</a>';
//        }
//    }
//    return a;
//}
/*отображение ячейки с тултипом. в тултипе - значение ячейки*/
function cellWithTooltipRenderer(value,metadata,record){
    metadata.tdAttr = 'data-qtip="' + value + '"';
    return value;
}

function jobStatusRenderer(a,b,record){
    var a = '';
    if  ( (typeof record == 'undefined') || (record == null) ) {
        return a;
    }

    if (record.data.startedAt == null){
        a = 'Не запущено';
    } else if (record.data.finishedAt == null){
        a = 'Выполняется';
    } else {
        a = record.data.success ? 'Выполнено успешно' : 'Ошибка';
    }
    return a;
}

function myDateRenderer(value){
    var r = Ext.util.Format.dateRenderer('d.m.Y');
    if (value != null) return r(new Date(value));
    return "";
}
function myDateRenderer2(value){
    var r = Ext.util.Format.dateRenderer('d.m.Y H:i:s');
    if (value != null) return r(new Date(value));
    return "";
}
function myPercentRenderer(value){
    return (value != null)  ? 
        value+"%" : "";
}

function boolRenderer(value) {
    return (value) ? 'Да' : 'Нет';
}

function addSeparatorsNF(nStr, inD, outD, sep) {
    nStr += '';
    var dpos = nStr.indexOf(inD);
    var nStrEnd = '';
    if (dpos != -1) {
        nStrEnd = outD + nStr.substring(dpos + 1, nStr.length);
        nStr = nStr.substring(0, dpos);
    }
    var rgx = /(\d+)(\d{3})/;
    while (rgx.test(nStr)) {
        nStr = nStr.replace(rgx, '$1' + sep + '$2');
    }
    return nStr + nStrEnd;
}

function floatRenderer(value) {
    if (value) {
        var val = value.toFixed(2);

        return addSeparatorsNF(val, '.', ',', '.');
    }
    else return "";
}

/*************************************************************/
/*********************** converters **************************/
/*************************************************************/
function priceCategoryName(v,item){
    return isEmpty(v) ? 
        item==null ? "" : ((item.price + ' ' + item.currency.shortTitle + ' (' + item.platformId + ')'))
        :
        v
    ;
}

function isEmpty(v){
    return (typeof v === 'undefined') || (v==null)  || (v.length==0); 
}

function getObjectTitle(v,obj){
    return isEmpty(v) ? 
        (obj==null? null : obj.title)
        :
        v;
}

function getObjectShortTitle(v,obj){
    return isEmpty(v) ?
            (obj==null? null : obj.shortTitle)
            :
            v;
}

function columnWrap(val) {
    return '<div style="white-space:normal !important;">' + val + '</div>';
}