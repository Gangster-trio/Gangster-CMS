var tt_bind = (function() {
	
	var listBindFlag=false,
		navigationLists=[],
		isShowList;
	
	/*绑定二级列表事件*/
	function bindEvent() {

		listBindFlag=true;

		var i = 0,
			slideItem = document.getElementsByClassName('sideSlip_navigation_item'),
			len = slideItem.length;
		for(i; i < len; i++) {

			var navigationTitle = slideItem[i].getElementsByClassName('sideSlip_navigation_out')[0],
				navigationList = slideItem[i].getElementsByTagName('ul')[0];

			if(navigationList) {

				navigationLists.push(navigationList);

				(function(navigationList,i) {

					navigationTitle.addEventListener('click', function(e) {
						
						var e=e||window.event;
						listStop(i);
						listShow(navigationList);

					});

				})(navigationList,i);

			}

		}

	}
	/*按钮点击事件绑定*/
	function slideMoveBind(id) {
		
		if(document.getElementsByClassName){
			
			var slideButton = getElememt(id),
				slide = document.getElementsByClassName('index_sideSlip')[0],
				mask = document.getElementsByClassName('index_sideSlip_mask')[0];
			slide.style.display='block'
			slideButton.addEventListener('click', function(e) {
				slideMove(slide, mask);
			});
			mask.addEventListener('click', function() {
				slideMove(slide, mask);
			})
			
			if(!listBindFlag){
				bindEvent();
			}
			
		}

	}
	/*传入侧滑控制按钮的ID或class，获取DOM元素*/
	function getElememt(id) {
		
		var classReg=new RegExp(id);
		
		var attrElement=document.getElementById(id);
		if(!attrElement){
	
			if(document.getElementsByClassName){
				attrElement=document.getElementsByClassName(id)[0];
			}
			else{
				var all=document.getElementsByTagName('*');
				for(var i=0;i<all.length;i++){
					if(all[i].className.test(classReg)){
						attrElement=all[i];
						break;
					}
				}
			}
		}
		return attrElement;

	}

	/*侧滑动作*/
	function slideMove(slide, mask) {
		
		slide.classList.toggle('sideSlip_off');
		mask.classList.toggle('sideSlip_mask_off');
		if(document.getElementsByTagName('body')[0].style.overflow=='hidden'){
			document.getElementsByTagName('body')[0].style.overflow='auto';
		}
		else{
			document.getElementsByTagName('body')[0].style.overflow='hidden';
		}

	}

	/*列表展开*/
	function listShow(obj) {
		
		obj.classList.toggle('sideSlip_list_off');

	}
	
	function listStop(off){
		
		var i=0,
			len=navigationLists.length;
			if(off==6){
				off=2;
			}
		for(i;i<len;i++){
			
			if(i!=off){
				navigationLists[i].classList.add('sideSlip_list_off');
			}
			
		}
		
	}
	
	return slideMoveBind;

})();

