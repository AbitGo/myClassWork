(function($){
	"use strict";
	//for gallery 1
	
	
	$(window).on('load',function(){
		var $rtGalleryContainer = $('.gallery-2 .gallery-wrapper');
		$rtGalleryContainer.isotope({
			filter: '*',
			animationOptions: {
				duration: 750,
				easing: 'linear',
				queue: false
			}
		});
		$('.isotop-classes-tab a').on('click',function(){
			$(this).closest('.isotop-classes-tab').find('.current').removeClass('current');
			$(this).addClass('current');     
			var selector = $(this).attr('data-filter');
			$rtGalleryContainer.isotope({
				filter: selector,
				animationOptions: {
					duration: 750,
					easing: 'linear',
					queue: false
				}
			});
			return false;
		}); 
	});
	

	var galleries = jQuery(".moods-masonry-gal>.gallery");
	if (galleries.length) {
		galleries.each(function(){
			var current = jQuery(this);
			var gal_id = jQuery(this).attr('id');
			current.imagesLoaded( function() {
				current.masonry();
			});
		});
	}
	
})(jQuery);

