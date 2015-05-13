<?php

// Moodle is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// Moodle is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with Moodle.  If not, see <http://www.gnu.org/licenses/>.

/**
 * External Web Service Template
 *
 * @package    localwstemplate
 * @copyright  2011 Moodle Pty Ltd (http://moodle.com)
 * @license    http://www.gnu.org/copyleft/gpl.html GNU GPL v3 or later
 */
require_once($CFG->libdir . "/externallib.php");

class local_competence_external extends external_api {#


    /**
     * get courses
     *
     * @return array Array of course objects
     * @since Moodle 2.5
     */

    public static function get_courses_for_user($useremail) {

        global $DB, $CFG, $USER;

        $query = 'SELECT c.id, c.fullname FROM {user} u INNER JOIN {user_enrolments} ue ON ue.userid = u.id INNER JOIN {enrol} e ON e.id = ue.enrolid INNER JOIN {course} c ON e.courseid = c.id WHERE u.email = ?';
        $result = $DB->get_records_sql($query, array($useremail));
        $mapper = function ($arrayElement) {
            return array('courseid' => $arrayElement->id, 'name' => $arrayElement->fullname);
        };
        $result_mapped = array_map($mapper, $result);
        return $result_mapped;
    }

    /**
     * Returns description of method parameters
     *
     * @return external_function_parameters
     * @since Moodle 2.5
     */
    public static function get_courses_for_user_parameters() {
        return new external_function_parameters(
                array(
            'user' => new external_value(PARAM_TEXT, 'multilang compatible name'),
                )
        );
    }

    /**
     * 
     * @return \external_multiple_structure
     */
    public static function get_courses_for_user_returns() {
        return new external_multiple_structure(
                new external_single_structure(
                array(
            'courseid' => new external_value(PARAM_INT, 'id of course'),
            'name' => new external_value(PARAM_TEXT, 'multilang compatible name, course unique'),
                )
                )
        );
    }

}
