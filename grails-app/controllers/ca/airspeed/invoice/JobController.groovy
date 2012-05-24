/*
    Copyright 2012 Airspeed Consulting

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

 */
package ca.airspeed.invoice

import org.springframework.dao.DataIntegrityViolationException

class JobController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [jobInstanceList: Job.list(params), jobInstanceTotal: Job.count()]
    }

    def create() {
        [jobInstance: new Job(params)]
    }

    def save() {
        def jobInstance = new Job(params)
        if (!jobInstance.save(flush: true)) {
            render(view: "create", model: [jobInstance: jobInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'job.label', default: 'Job'), jobInstance.id])
        redirect(action: "show", id: jobInstance.id)
    }

    def show() {
        def jobInstance = Job.get(params.id)
        if (!jobInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'job.label', default: 'Job'), params.id])
            redirect(action: "list")
            return
        }

        [jobInstance: jobInstance]
    }

    def edit() {
        def jobInstance = Job.get(params.id)
        if (!jobInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'job.label', default: 'Job'), params.id])
            redirect(action: "list")
            return
        }

        [jobInstance: jobInstance]
    }

    def update() {
        def jobInstance = Job.get(params.id)
        if (!jobInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'job.label', default: 'Job'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (jobInstance.version > version) {
                jobInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'job.label', default: 'Job')] as Object[],
                          "Another user has updated this Job while you were editing")
                render(view: "edit", model: [jobInstance: jobInstance])
                return
            }
        }

        jobInstance.properties = params

        if (!jobInstance.save(flush: true)) {
            render(view: "edit", model: [jobInstance: jobInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'job.label', default: 'Job'), jobInstance.id])
        redirect(action: "show", id: jobInstance.id)
    }

    def delete() {
        def jobInstance = Job.get(params.id)
        if (!jobInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'job.label', default: 'Job'), params.id])
            redirect(action: "list")
            return
        }

        try {
            jobInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'job.label', default: 'Job'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'job.label', default: 'Job'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
