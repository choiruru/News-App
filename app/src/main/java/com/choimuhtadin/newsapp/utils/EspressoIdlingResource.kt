/*
 * Copyright 2015, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.choimuhtadin.newsapp.utils;

import androidx.test.espresso.IdlingResource;

/**
 * Contains a static reference to {@link androidx.test.espresso.IdlingResource}, only available in the 'mock' build type.
 */
class EspressoIdlingResource {

    companion object{
        private const val TAG = "EspressoIdlingResource"

        private const val RESOURCE = "GLOBAL";

        private val mCountingIdlingResource = EspressoCountingIdlingResource(RESOURCE);

        fun increment() {
            mCountingIdlingResource.increment();
        }

        fun decrement() {
            mCountingIdlingResource.decrement();
        }

        fun getIdlingResource(): IdlingResource  {
            return mCountingIdlingResource;
        }
    }
}
