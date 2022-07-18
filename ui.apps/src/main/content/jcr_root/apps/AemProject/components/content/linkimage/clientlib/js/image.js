/*******************************************************************************
 * Copyright 2017 Adobe Systems Incorporated
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
;(function(CQ) {
    CQ.WCM = CQ.WCM || {};
    CQ.WCM.Image = CQ.WCM.Image || function() {
            var self = {};
            var CONST = {
                MAP_SELECTOR: ".cq-wcm-foundation-image-map"
            };

            self.handleResize = function() {
                var elements = document.querySelectorAll(CONST.MAP_SELECTOR);

                Array.prototype.forEach.call(elements, function(el) {
                    if(!el.getAttribute("usemap")) {
                        return;
                    }
                    var imgSrc = el.getAttribute("src"),
                        onLoadImg = document.createElement("img");

                    onLoadImg.addEventListener("load", function() {
                        var imgWidth = el.getAttribute("width") || el.naturalWidth,
                            imgHeight = el.getAttribute("height") || el.naturalHeight;

                        if(!imgWidth || !imgHeight) {
                            var tempImage = new Image();
                            tempImage.src = imgSrc;
                            if(!imgWidth) {
                                imgWidth = tempImage.width;
                            }
                            if(!imgHeight) {
                                imgHeight = tempImage.height;
                            }
                        }

                        var relWidth = el.offsetWidth / 100,
                            relHeight = el.offsetHeight / 100,
                            map = el.getAttribute("usemap");
                        map = map.replace('#', '');

                        var maps = document.querySelectorAll('map[name="' + map + '"]');
                        Array.prototype.forEach.call(maps, function(mapEl) {
                            var areas = mapEl.querySelectorAll("area");
                            Array.prototype.forEach.call(areas, function(areaEl) {
                                if(!areaEl.dataset.coords) {
                                    areaEl.dataset.coords = areaEl.getAttribute("coords");
                                }

                                var coords = areaEl.dataset.coords.split(','),
                                    relCoords = new Array(coords.length);

                                for(var i = 0; i < coords.length; ++i) {
                                    if(i % 2 == 0) {
                                        relCoords[i] = parseInt(((coords[i] / imgWidth) * 100) * relWidth);
                                    } else {
                                        relCoords[i] = parseInt(((coords[i] / imgHeight) * 100) * relHeight);
                                    }
                                }
                                areaEl.setAttribute("coords", relCoords.toString());
                            });
                        });
                    });
                    onLoadImg.setAttribute("src", imgSrc);
                });
            };

            return self;
        }();

    var addEventListener = window.addEventListener || window.attachEvent,
        MutationObserver = window.MutationObserver || window.WebKitMutationObserver || window.MozMutationObserver,
        mutationConfig = {
            attributes: true
        },
        observer = new MutationObserver(function(mutations) {
            mutations.forEach(function () {
                CQ.WCM.Image.handleResize();
            });
        });

    addEventListener("resize", CQ.WCM.Image.handleResize);
    observer.observe(document, mutationConfig);
    CQ.WCM.Image.handleResize();
})(CQ);