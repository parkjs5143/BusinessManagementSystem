
// 거래처 리스트 가져오기
axios.get('/api/list_custom')
    .then(function (response) {
        console.log(response);
        for(let i in response.data){
            let busiNum = response.data[i].busiNum+'';
            let custom = response.data[i].custom;
            console.log(typeof(busiNum));
            console.log(busiNum);
            let tr = $('<tr class="list_item" style="cursor: pointer;" onclick="showDetail(\''+busiNum+'\')">').append(
                '<td>' + busiNum.trim() + '</td>' +
                '<td>'+ custom + '</td>' +
                '</tr>'
            );
            $('.account_box tbody').append(tr);
        }
    });

// 조회 클릭(검색)
function clickSerarchBtn(){
    let searchNum = $('#busi_num_search_input').val();
    if(searchNum === '') searchNum = null;

    let searchCustom = $('#custom_search_input').val();
    if(searchCustom === '') searchCustom = null;

    if((searchNum === null || searchNum === '') && (searchCustom === null || searchCustom ==='')) alert('데이터를 입력해주세요.');

    console.log(searchNum);
    console.log(searchCustom);

    axios.request({
        method: "POST",
        url: "/api/search_list",
        headers: {'Content-type': 'application/json'},
        data: {
            data: {
                busiNum : searchNum,
                custom : searchCustom
            }
        }
    }).then(function (response){
        console.log(response);
        if(response.data.length > 0) {
            $('.list_item').remove();
            for(let i in response.data){
                    let busiNum = response.data[i].busiNum+'';
                    let custom = response.data[i].custom;
                    let tr = $('<tr class="list_item" style="cursor: pointer;" onclick="showDetail('+busiNum+'num'+')">').append(
                        '<td>' + busiNum.trim() + '</td>' +
                        '<td>'+ custom + '</td>' +
                        '</tr>'
                    );
                    $('.account_box tbody').append(tr);
            }
        } else {
            alert('데이터가 존재하지 않습니다!')
        }
    });
}

// 화면 출력(디테일)
function showDetail(busiNum){
    console.log(busiNum);

    axios.get('/api/read_custom/'+busiNum)
        .then(function (response){
            // 버튼 활성화 & 비활성화
            $('#btn_submit').prop('disabled',true);
            $('#btn_modify').prop('disabled',false);
            $('#btn_delete').prop('disabled',false);
            $('#busi_num_input').prop('readonly',true);
            // 변경정보 비활성화
            $('#modi_info_man_input').prop('readonly', false);
            // 등록정보 비활성화
            $('#regi_info_man_input').prop('readonly', true);

            console.log(response);
            const busiNum = response.data.data.busiNum;
            const shortName = response.data.data.shortName;
            const custom = response.data.data.custom;
            const ceo = response.data.data.ceo;
            const chargePerson = response.data.data.chargePerson;
            const busiCondition = response.data.data.busiCondition;
            const item = response.data.data.item;
            const postNum = response.data.data.postNum;
            const addr1 = response.data.data.addr1;
            const addr2 = response.data.data.addr2;
            const tel = response.data.data.tel;
            const fax = response.data.data.fax;
            const homepage = response.data.data.homepage;
            const coYn = response.data.data.coYn;
            const foreignYn = response.data.data.foreignYn;
            const taxYn = response.data.data.taxYn;
            const countryEng = response.data.data.countryEng;
            const countryKor = response.data.data.countryKor;
            const specialRelation = response.data.data.specialRelation; // 0 or 1
            const tradeStop = response.data.data.tradeStop; // 0 or 1
            let contractPeriodS = response.data.data.contractPeriodS; // date
            if(contractPeriodS!=null) {
                contractPeriodS = contractPeriodS.slice(0, 10);
            }
            let contractPeriodE = response.data.data.contractPeriodE; // date
            if(contractPeriodS!=null) {
                contractPeriodE = contractPeriodE.slice(0,10);
            }
            const regiInfoMan = response.data.data.regiInfoMan;
            const regiInfoDate = response.data.data.regiInfoDate; // date
            const modiInfoMan = response.data.data.modiInfoMan;
            const modiInfoDate = response.data.data.modiInfoDate; // date

            const factory = response.data.data.factory;
            const tradeBank = response.data.data.tradeBank;
            const accountNum = response.data.data.accountNum;

            $('#curr_busi_num').val(busiNum); // 사업자번호 저장용
            $('#busi_num_input').val(busiNum.trim());    // input 박스용
            $('#short_input').val(shortName);
            $('#custom_input').val(custom);
            $('#ceo_input').val(ceo);
            $('#charge_person_input').val(chargePerson);
            $('#busi_condition_input').val(busiCondition);
            $('#item_input').val(item);
            $('#post_num_input').val(postNum);
            $('#addr1_input').val(addr1);
            $('#addr2_input').val(addr2);
            $('#tel_input').val(tel);
            $('#fax_input').val(fax);
            $('#homepage_input').val(homepage);
            if(coYn===1){
                $("input:radio[name='corp']:radio[value='1']").prop('checked', true);
            } else {
                $("input:radio[name='corp']:radio[value='0']").prop('checked', true);
            }
            if(foreignYn===1){
                $("input:radio[name='foreign']:radio[value='1']").prop('checked', true);
            } else {
                $("input:radio[name='foreign']:radio[value='0']").prop('checked', true);
            }
            if(taxYn===1){
                $('#select_tax').val('1').prop("selected",true);
            }else{
                $('#select_tax').val('0').prop("selected",true);
            }
            $('#country_eng').val(countryEng);
            $('#country_kor').val(countryKor);
            switch (countryKor){
                case '대한민국' :
                    $('#country_select').val('KOR 대한민국').prop("selected",true);
                    break;
                case '미국' :
                    $('#country_select').val('USA 미국').prop("selected",true);
                    break;
                case '일본' :
                    $('#country_select').val('JPN 일본').prop("selected",true);
                    break;
                case '프랑스' :
                    $('#country_select').val('FRA 프랑스').prop("selected",true);
                    break;
            }
            if(specialRelation===1) {
                $('input:checkbox[name="special_relation"]').prop('checked',true);
            } else $('input:checkbox[name="special_relation"]').prop('checked',false);
            if(tradeStop===1){
                $('input:checkbox[name="trade_stop"]').prop('checked',true);
            } else $('input:checkbox[name="trade_stop"]').prop('checked',false);
            $('#contract_period_s_input').val(contractPeriodS);
            $('#contract_period_e_input').val(contractPeriodE);
            $('#regi_info_man_input').val(regiInfoMan);
            $('#regi_info_date_input').val(regiInfoDate);
            $('#modi_info_man_input').val(modiInfoMan);
            $('#modi_info_date_input').val(modiInfoDate);
            $('#factory_input').val(factory);
            $('#trade_bank_input').val(tradeBank);
            $('#account_num_input').val(accountNum);
        });
}

// 다음포스트 서비스
function sample6_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {

            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if(data.userSelectedType === 'R'){
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }
            } else { extraAddr = ''; }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('post_num_input').value = data.zonecode;
            document.getElementById("addr1_input").value = addr;
            document.getElementById("addr2_input").value = extraAddr;
            document.getElementById("addr1_input").setAttribute('validateresult','true');
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("addr2_input").focus();
        }
    }).open();
}

// 팝업창 open
function openPop() {
    $(".layer").css("display","block"); //팝업창 display block
}
// 팝업창 닫기
function closePop() {
    $(".layer").css("display","none");
}
// 팝업창 확인 버튼 실행
function submitPop(){
    const country = $("#country_select option:selected").val();
    const eng = country.split(' ')[0];
    const kor = country.split(' ')[1];
    $("#country_eng").val(eng);
    $("#country_kor").val(kor);

    closePop();
}

// 초기화 버튼 클릭
function reset(){
    // 버튼 활성화 & 비활성화
    $('#btn_submit').prop('disabled',false);
    $('#btn_modify').prop('disabled',true);
    $('#btn_delete').prop('disabled',true);
    $('#busi_num_input').prop('readonly',false);
    // 변경정보 활성화
    $('#modi_info_man_input').prop('readonly', true);
    // 등록정보 비활성화
    $('#regi_info_man_input').prop('readonly', false);

    // input 값 초기화
    $('#curr_busi_num').val('');
    $('#busi_num_input').val('');
    $('#short_input').val('');
    $('#custom_input').val('');
    $('#ceo_input').val('');
    $('#charge_person_input').val('');
    $('#busi_condition_input').val('');
    $('#item_input').val('');
    $('#post_num_input').val('');
    $('#addr1_input').val('');
    $('#addr2_input').val('');
    $('#tel_input').val('');
    $('#fax_input').val('');
    $('#homepage_input').val('');
    $("input:radio[name='corp']:radio[value='1']").prop('checked', true);
    $("input:radio[name='corp']:radio[value='0']").prop('checked', false);
    $("input:radio[name='foreign']:radio[value='1']").prop('checked', false);
    $("input:radio[name='foreign']:radio[value='0']").prop('checked', true);
    $('#select_tax').val('1').prop("selected",true);
    $('#country_eng').val('KOR');
    $('#country_kor').val('대한민국');
    $('#country_select').val('KOR 대한민국').prop("selected",true);
    $('input:checkbox[name="special_relation"]').prop('checked',false);
    $('input:checkbox[name="trade_stop"]').prop('checked',false);
    $('#contract_period_s_input').val('');
    $('#contract_period_e_input').val('');
    $('#regi_info_man_input').val('');
    $('#regi_info_date_input').val('');
    $('#modi_info_man_input').val('');
    $('#modi_info_date_input').val('');
    $('#factory_input').val('');
    $('#trade_bank_input').val('');
    $('#account_num_input').val('');
}

// 등록버튼 클릭
function registCustom(){
    let busiNum = $('#busi_num_input').val();
    const custom = $('#custom_input').val();

    if(busiNum==null || busiNum=='') { alert('사업자번호를 입력해주세요.') }
    else if(custom==null || custom=='') { alert('거래처명을 입력해주세요.') }
    else {  // 둘다 입력했을 경우
        const shortName = $('#short_input').val();
        const ceo = $('#ceo_input').val();
        const chargePerson = $('#charge_person_input').val();
        const busiCondition = $('#busi_condition_input').val();
        const item = $('#item_input').val();
        const postNum = $('#post_num_input').val();
        const addr1 = $('#addr1_input').val();
        const addr2 = $('#addr2_input').val();
        const tel = $('#tel_input').val();
        const fax = $('#fax_input').val();
        const homepage = $('#homepage_input').val();
        const coYn = $('input[name="corp"]:checked').val(); // 0 or 1
        const foreignYn = $('input[name="foreign"]:checked').val(); // 0 or 1
        const taxYn = $("select[name=tax]").val();  //0 or 1
        const countryEng = $('#country_eng').val();
        const countryKor = $('#country_kor').val();

        let specialRelation = $('input:checkbox[name="special_relation"]').is(':checked');
        if (specialRelation == true) specialRelation = 1;
        else specialRelation = 0;

        let tradeStop = $('input:checkbox[name="trade_stop"]').is(':checked');
        console.log(tradeStop);
        if (tradeStop == true) tradeStop = 1;
        else tradeStop = 0;
        console.log(tradeStop);

        const contractPeriodS = $('#contract_period_s_input').val();
        const contractPeriodE = $('#contract_period_e_input').val();
        const regiInfoMan = $('#regi_info_man_input').val();
        const regiInfoDate = new Date();
        const factory = $('#factory_input').val();
        const tradeBank = $('#trade_bank_input').val();
        const accountNum = $('#account_num_input').val();

        // 엑시오스 제출
        axios.request({
            method: "POST",
            url: "/api/regist_custom",
            headers: {'Content-type': 'application/json'},
            data: {
                data: {
                    busiNum: busiNum,
                    custom: custom,
                    shortName: shortName,
                    ceo: ceo,
                    chargePerson: chargePerson,
                    busiCondition: busiCondition,
                    item: item,
                    postNum: postNum,
                    addr1: addr1,
                    addr2: addr2,
                    tel: tel,
                    fax: fax,
                    homepage: homepage,
                    coYn: coYn,
                    foreignYn: foreignYn,
                    taxYn: taxYn,
                    countryEng: countryEng,
                    countryKor: countryKor,
                    specialRelation: specialRelation,
                    tradeStop: tradeStop,
                    contractPeriodS: contractPeriodS,
                    contractPeriodE: contractPeriodE,
                    regiInfoMan: regiInfoMan,
                    regiInfoDate: regiInfoDate,
                    factory: factory,
                    tradeBank: tradeBank,
                    accountNum: accountNum,
                }
            }
        }).then(function(response) {
            console.log(response);
            alert('저장되었습니다.');
            location.reload();
        }).catch(function (err) {
            console.log(err);
        });
    }
}

// 수정 버튼 클릭
function modifyCustom(){
    const currBusiNum =  $('#curr_busi_num').val();

    const shortName = $('#short_input').val();
    const custom = $('#custom_input').val();
    const ceo = $('#ceo_input').val();
    const chargePerson = $('#charge_person_input').val();
    const busiCondition = $('#busi_condition_input').val();
    const item = $('#item_input').val();
    const postNum = $('#post_num_input').val();
    const addr1 = $('#addr1_input').val();
    const addr2 = $('#addr2_input').val();
    const tel = $('#tel_input').val();
    const fax = $('#fax_input').val();
    const homepage = $('#homepage_input').val();
    const coYn = $('input[name="corp"]:checked').val(); // 0 or 1
    const foreignYn = $('input[name="foreign"]:checked').val(); // 0 or 1
    const taxYn = $("select[name=tax]").val();  //0 or 1
    const countryEng = $('#country_eng').val();
    const countryKor = $('#country_kor').val();

    let specialRelation = $('input:checkbox[name="special_relation"]').is(':checked');
    if (specialRelation == true) specialRelation = 1;
    else specialRelation = 0;

    let tradeStop = $('input:checkbox[name="trade_stop"]').is(':checked');
    if (tradeStop == true) tradeStop = 1;
    else tradeStop = 0;

    const contractPeriodS = $('#contract_period_s_input').val();
    const contractPeriodE = $('#contract_period_e_input').val();
    const regiInfoMan = $('#regi_info_man_input').val();
    const regiInfoDate = $('#regi_info_date_input').val();
    const modiInfoMan = $('#modi_info_man_input').val();
    const modiInfoDate = new Date();
    const factory = $('#factory_input').val();
    const tradeBank = $('#trade_bank_input').val();
    const accountNum = $('#account_num_input').val();

    // 엑시오스 제출
    axios.request({
        method: "PUT",
        url: "/api/update_custom",
        headers: {'Content-type': 'application/json'},
        data: {
            data: {
                busiNum: currBusiNum,
                custom: custom,
                shortName: shortName,
                ceo: ceo,
                chargePerson: chargePerson,
                busiCondition: busiCondition,
                item: item,
                postNum: postNum,
                addr1: addr1,
                addr2: addr2,
                tel: tel,
                fax: fax,
                coYn: coYn,
                homepage : homepage,
                foreignYn: foreignYn,
                taxYn: taxYn,
                countryEng: countryEng,
                countryKor: countryKor,
                specialRelation: specialRelation,
                tradeStop: tradeStop,
                contractPeriodS: contractPeriodS,
                contractPeriodE: contractPeriodE,
                regiInfoMan: regiInfoMan,
                regiInfoDate: regiInfoDate,
                modiInfoMan: modiInfoMan,
                modiInfoDate: modiInfoDate,
                factory: factory,
                tradeBank: tradeBank,
                accountNum: accountNum,
            }
        }
    }).then(function(response) {
        console.log(response);
        alert('수정되었습니다.');
        location.reload();
    }).catch(function (err) {
        alert('수정실패했습니다.');
        console.log(err);
    });
}

// 삭제버튼 클릭
function deleteCustom(){
    const currBusiNum = $('#curr_busi_num').val();
    axios.delete('/api/delete_custom/'+currBusiNum)
        .then(function(response){
            console.log(response);
            if(response.data.resultCode === 'ERROR')
                alert('삭제실패했습니다.');
            else alert('삭제되었습니다.');
                location.reload();
        });
}